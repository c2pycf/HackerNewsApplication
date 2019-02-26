package com.demo.fred.hackerdemo.ui.presenter

import android.os.Bundle
import com.demo.fred.hackerdemo.model.NewsResponse
import com.demo.fred.hackerdemo.ui.adapter.NewsListAdapter
import com.demo.fred.hackerdemo.ui.contract.NewsListContract
import com.demo.fred.hackerdemo.ui.model.NewsListModel
import com.demo.fred.hackerdemo.ui.view.NewsDetailFragment
import com.demo.fred.hackerdemo.ui.view.NewsWebViewFragment
import com.demo.fred.hackerdemo.utils.Constants.Companion.KEY_NEWS
import com.demo.fred.hackerdemo.utils.Constants.Companion.KEY_URL
import com.demo.fred.hackerdemo.utils.Constants.Companion.URL_PREFIX
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


/**
 * News list presenter class,
 * bind view and model class to hold the data and present the logic
 * consume the observable response from server
 */
class NewsListPresenter @Inject constructor(private val model: NewsListModel) : NewsListContract.IPresenter {


    private var disposable = CompositeDisposable()
    private var adapter: NewsListAdapter = NewsListAdapter()
    private var iView: NewsListContract.IView? = null

    override fun attachView(mView: NewsListContract.IView) {
        iView = mView
    }


    override fun onStart() {
        iView?.showProgressBar()
        iView?.setAdapter(adapter)
        loadNews()
    }

    override fun loadingList() {
        loadNews()
    }

    override fun onSetUp() {
        adapter.onItemClick { newsResponse ->
            if (checkLink(newsResponse))
                onExternalLinkClicked(newsResponse)
            else
                onContentNewsClicked(newsResponse)

        }
    }

    private fun checkLink(newsResponse: NewsResponse): Boolean {
        return newsResponse.url.startsWith(URL_PREFIX)
    }

    private fun loadNews() {
        val hackerNewsObservable: Observable<NewsResponse> = model.getHackTopNews()
            .flatMapIterable { ids ->
                ids
            }.flatMap { index ->
                model.getHackNews(index)
            }
        disposable.add(
            hackerNewsObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : Observer<NewsResponse>, Disposable {
                    override fun isDisposed(): Boolean {
                        return false
                    }

                    override fun dispose() {
                        disposable.dispose()
                    }

                    override fun onComplete() {
                        iView?.stopProgressBar()
                    }

                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onNext(t: NewsResponse) {
                        adapter.addNews(t)
                    }

                    override fun onError(e: Throwable) {
                        iView?.showMessage(e.message.orEmpty())
                    }

                })
        )
    }


    override fun onExternalLinkClicked(newsResponse: NewsResponse) {
        val bundle = Bundle()
        bundle.putString(KEY_URL, newsResponse.url)
        val fragment = NewsWebViewFragment()
        fragment.arguments = bundle
        iView?.openNewView(fragment)
    }

    override fun onContentNewsClicked(newsResponse: NewsResponse) {
        val bundle = Bundle()
        bundle.putSerializable(KEY_NEWS, newsResponse)
        val fragment = NewsDetailFragment()
        fragment.arguments = bundle
        iView?.openNewView(fragment)
    }

    override fun onClear() {
        if (!disposable.isDisposed) {
            disposable.clear()
        }
    }


}