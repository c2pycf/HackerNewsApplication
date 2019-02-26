package com.demo.fred.hackerdemo.ui.contract

import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import com.demo.fred.hackerdemo.model.NewsResponse

/**
 * Contract class for NewsList MVP structure
 */
interface NewsListContract {
    interface IView {
        fun showProgressBar()

        fun stopProgressBar()

        fun showMessage(message: String)

        fun setAdapter(adapter: RecyclerView.Adapter<*>)

        fun openNewView(fragment: Fragment)
    }

    interface IPresenter {
        fun onStart()

        fun attachView(mView: IView)

        fun loadingList()

        fun onSetUp()

        fun onExternalLinkClicked(newsResponse: NewsResponse)

        fun onContentNewsClicked(newsResponse: NewsResponse)

        fun onClear()

    }
}