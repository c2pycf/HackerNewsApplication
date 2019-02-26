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
import android.widget.ProgressBar
import com.demo.fred.hackerdemo.R
import com.demo.fred.hackerdemo.ui.contract.NewsListContract
import com.demo.fred.hackerdemo.ui.presenter.NewsListPresenter
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_news_list.view.*
import javax.inject.Inject

/**
 *NewsList Fragment view class
 * Showing the list of hacker news with title, author and time in recycler view
 * connect presenter and updates view when listen to the event
 */
class NewsListFragment : Fragment(), NewsListContract.IView {

    private lateinit var recyclerView: RecyclerView
    private lateinit var processBar: ProgressBar

    @Inject
    lateinit var mPresenter: NewsListPresenter

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
        activity?.actionBar?.title = resources.getString(R.string.fragment_news_list_title)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_news_list, container, false)
        mPresenter.attachView(this)
        recyclerView = view.recycler_view_news_list
        processBar = view.progress_news_list
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.itemAnimator = DefaultItemAnimator()
        mPresenter.onStart()
    }

    override fun onStart() {
        super.onStart()
        mPresenter.onSetUp()
    }

    override fun onStop() {
        super.onStop()
        mPresenter.onClear()
    }

    override fun setAdapter(adapter: RecyclerView.Adapter<*>) {
        recyclerView.adapter = adapter
    }

    override fun showProgressBar() {
        processBar.visibility = View.VISIBLE
    }

    override fun stopProgressBar() {
        processBar.visibility = View.GONE
    }

    override fun showMessage(message: String) {
        Snackbar.make(recyclerView, message, Snackbar.LENGTH_LONG).show()
    }


    override fun openNewView(fragment: Fragment) {
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.fragment_container, fragment)
            ?.addToBackStack(null)
            ?.commit()
    }
}