package com.demo.fred.hackerdemo.ui.view

import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.demo.fred.hackerdemo.R
import com.demo.fred.hackerdemo.model.NewsResponse
import com.demo.fred.hackerdemo.ui.contract.NewsDetailContract
import com.demo.fred.hackerdemo.ui.presenter.NewsDetailPresenter
import com.demo.fred.hackerdemo.utils.Constants.Companion.KEY_NEWS
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_news_detail.view.*
import javax.inject.Inject

/**
 *NewsDetail Fragment view class
 * Showing the detail of hacker news if it do not have external Link
 * connect presenter and updates view when listen to the event
 */
class NewsDetailFragment : Fragment(), NewsDetailContract.IView {

    @Inject
    lateinit var mPresenter: NewsDetailPresenter
    private lateinit var commentList: RecyclerView

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_news_detail, container, false)
        //mPresenter = NewsDetailPresenter(this)
        mPresenter.attachView(this)
        commentList = view.recycler_view_comments
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = resources.getString(R.string.fragment_news_list_title)
        activity?.actionBar?.setDisplayHomeAsUpEnabled(true)
        mPresenter.loadingNews(arguments?.getSerializable(KEY_NEWS) as NewsResponse)
        mPresenter.create()
        commentList.itemAnimator = DefaultItemAnimator()
        view.bt_show_comments.setOnClickListener {
            if (commentList.visibility == View.GONE) {
                mPresenter.loadsComment()
                commentList.visibility = View.VISIBLE
            } else mPresenter.loadsComment()
        }
    }


    override fun loadingComments() {
        mPresenter.loadsComment()
    }

    override fun setAdapter(adapter: RecyclerView.Adapter<*>) {
        commentList.adapter = adapter
    }

    override fun onDisplayText(
        title: String,
        content: String,
        decedents: Int?,
        by: String?,
        score: Int?,
        time: String
    ) {
        view?.tv_news_title_detail?.text = title
        view?.tv_news_detail_time?.text = time
        view?.tv_content?.text = content
        view?.tv_news_detail_author?.text = resources.getString(R.string.tv_detail_author).plus(by)
        view?.tv_news_detail_score?.text = resources.getString(R.string.tv_detail_score).plus(score)
        view?.tv_news_detail_decedents?.text = resources.getString(R.string.tv_detail_dec).plus(decedents)
    }

    override fun setProgressBar(boolean: Boolean) {

    }

    override fun showMessage(message: String) {
        Snackbar.make(commentList, message, Snackbar.LENGTH_LONG).show()
    }


}