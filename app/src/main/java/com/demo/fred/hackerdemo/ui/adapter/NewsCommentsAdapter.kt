package com.demo.fred.hackerdemo.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.demo.fred.hackerdemo.R
import com.demo.fred.hackerdemo.model.NewsResponse
import com.demo.fred.hackerdemo.utils.formatHTMLText
import com.demo.fred.hackerdemo.utils.formatTime
import kotlinx.android.synthetic.main.item_news_comment.view.*

/**
 * News comment adapter for comment list
 * shows user comments
 */
class NewsCommentsAdapter : RecyclerView.Adapter<NewsCommentsAdapter.CommentsViewHolder>() {
    private val newsList = ArrayList<NewsResponse>()
    private var itemClickBehavior: ((NewsResponse) -> Unit)? = null

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CommentsViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.item_news_comment, p0, false)
        return CommentsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onBindViewHolder(p0: CommentsViewHolder, p1: Int) {
        p0.bindView(newsList[p1], itemClickBehavior)
    }

    fun addNews(news: NewsResponse) {
        newsList.add(news)
        notifyItemChanged(newsList.size)
    }

    fun onItemClick(itemClickBehavior: (NewsResponse) -> Unit) {
        this.itemClickBehavior = itemClickBehavior
    }

    class CommentsViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bindView(
            data: NewsResponse,
            itemClickBehavior: ((NewsResponse) -> Unit)?
        ) {

            view.tv_comment_content.text = formatHTMLText(data.url)
            view.tv_comment_name.text = data.by.orEmpty()
            if (data.time != null) {
                val timeFormat = formatTime(data.time)
                view.tv_comment_time.text = timeFormat
            }
            view.setOnClickListener {
                itemClickBehavior?.invoke(data)
            }

        }

    }
}