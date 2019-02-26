package com.demo.fred.hackerdemo.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.demo.fred.hackerdemo.R
import com.demo.fred.hackerdemo.model.NewsResponse
import com.demo.fred.hackerdemo.utils.formatTime
import kotlinx.android.synthetic.main.item_news.view.*

/**
 * News list adapter for news list
 */
class NewsListAdapter : RecyclerView.Adapter<NewsListAdapter.NewsViewHolder>() {
    private val newsList = ArrayList<NewsResponse>()
    private var itemClickBehavior: ((NewsResponse) -> Unit)? = null

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): NewsViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.item_news, p0, false)
        return NewsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onBindViewHolder(p0: NewsViewHolder, p1: Int) {
        p0.bindView(newsList[p1], itemClickBehavior)
    }

    fun addNews(news: NewsResponse) {
        newsList.add(news)
        notifyItemChanged(newsList.size)
    }

    fun onItemClick(itemClickBehavior: (NewsResponse) -> Unit) {
        this.itemClickBehavior = itemClickBehavior
    }

    class NewsViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bindView(
            data: NewsResponse,
            itemClickBehavior: ((NewsResponse) -> Unit)?
        ) {

            view.tv_news_title.text = data.title
            view.tv_news_author.text = view.resources.getString(R.string.tv_detail_author).plus(data.by)
            if (data.time != null) {
                val timeFormat = formatTime(data.time)
                view.tv_news_time.text = timeFormat
            }
            view.setOnClickListener {
                itemClickBehavior?.invoke(data)
            }
        }

    }
}