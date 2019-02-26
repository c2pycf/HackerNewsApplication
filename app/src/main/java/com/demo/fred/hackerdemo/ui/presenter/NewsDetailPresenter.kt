package com.demo.fred.hackerdemo.ui.presenter

import com.demo.fred.hackerdemo.model.NewsResponse
import com.demo.fred.hackerdemo.ui.adapter.NewsCommentsAdapter
import com.demo.fred.hackerdemo.ui.contract.NewsDetailContract
import com.demo.fred.hackerdemo.ui.model.NewsDetailModel
import com.demo.fred.hackerdemo.utils.Constants.Companion.TAG_COMMENT
import com.demo.fred.hackerdemo.utils.formatHTMLText
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

/**
 * News Detail presenter class,
 * bind view and model class to hold the data and present the logic
 */
class NewsDetailPresenter @Inject constructor(private val model: NewsDetailModel) : NewsDetailContract.IPresenter {

    private var mView: NewsDetailContract.IView? = null
    private val disposable = CompositeDisposable()
    private lateinit var commentItems: List<Int>
    private lateinit var adapter: NewsCommentsAdapter


    override fun attachView(mView: NewsDetailContract.IView) {
        this.mView = mView
    }

    override fun loadingNews(newsResponse: NewsResponse) {
        commentItems = newsResponse.kids ?: ArrayList()
        val content = formatContent(newsResponse.url)
        val time = formatTime(newsResponse.time)
        mView?.onDisplayText(
            newsResponse.title.orEmpty(),
            content,
            newsResponse.descendants,
            newsResponse.by,
            newsResponse.score,
            time
        )
    }

    override fun create() {
        adapter = NewsCommentsAdapter()
        mView?.setAdapter(adapter)
    }

    private fun formatTime(time: Long?): String {
        if (time != null) {
            return com.demo.fred.hackerdemo.utils.formatTime(time)
        }
        return "0"
    }

    private fun formatContent(content: String): String {
        return formatHTMLText(content)
    }

    override fun loadsComment() {
        mView?.setProgressBar(true)
        if (commentItems.isNotEmpty())
            loadComments(commentItems)

    }

    private fun loadComments(kids: List<Int>) {
        val hackerCommentsObservable: Observable<NewsResponse> = Observable.just(kids)
            .flatMapIterable { ids ->
                ids
            }.flatMap { index ->
                model.getHackNews(index)
            }.filter {
                it.type.equals(TAG_COMMENT)
            }
        disposable.add(
            hackerCommentsObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    mView?.setProgressBar(false)
                    adapter.addNews(it)
                }, {
                    mView?.setProgressBar(false)
                    mView?.showMessage(it.message.orEmpty())
                })
        )
    }

}