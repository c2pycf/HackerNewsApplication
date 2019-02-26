package com.demo.fred.hackerdemo.ui.view

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import com.demo.fred.hackerdemo.R
import com.demo.fred.hackerdemo.utils.Constants.Companion.KEY_URL
import dagger.android.support.AndroidSupportInjection

/**
 * News web Fragment view class
 * Display web view according to the external link
 */
class NewsWebViewFragment : Fragment() {
    private lateinit var newsWebView: WebView

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_news_web, container, false)
        newsWebView = view.findViewById(R.id.web_news)
        if (arguments?.getString(KEY_URL) != null)
            newsWebView.loadUrl(arguments?.getString(KEY_URL))
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.actionBar?.title = resources.getString(R.string.fragment_news_list_title)
        activity?.actionBar?.setDisplayHomeAsUpEnabled(true)
    }
}