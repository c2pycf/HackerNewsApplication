package com.demo.fred.hackerdemo.ui.contract

import android.support.v7.widget.RecyclerView
import com.demo.fred.hackerdemo.model.NewsResponse

/**
 * Contract class for NewsDetail MVP structure
 */
interface NewsDetailContract {
    interface IView {
        fun loadingComments()

        fun onDisplayText(
            title: String,
            content: String,
            decedents: Int?,
            by: String?,
            score: Int?,
            time: String
        )

        fun setProgressBar(boolean: Boolean)
        fun showMessage(message: String)
        fun setAdapter(adapter: RecyclerView.Adapter<*>)
    }

    interface IPresenter {
        fun loadingNews(newsResponse: NewsResponse)

        fun loadsComment()

        fun create()

        fun attachView(mView: NewsDetailContract.IView)
    }
}