package com.demo.fred.hackerdemo.ui.model

import com.demo.fred.hackerdemo.model.NewsResponse
import com.demo.fred.hackerdemo.net.HackerClient
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Model lass for NewsList MVP structure to connect repository layer
 */
class NewsListModel @Inject constructor(private val hackerClient: HackerClient) {

    fun getHackTopNews(): Observable<List<Int>> {
        return hackerClient.getHackerTopNews()
    }

    fun getHackNews(id: Int): Observable<NewsResponse> {
        return hackerClient.getHackerNewsItem(id)
    }

}