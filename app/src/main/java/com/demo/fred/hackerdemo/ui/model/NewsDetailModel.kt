package com.demo.fred.hackerdemo.ui.model

import com.demo.fred.hackerdemo.model.NewsResponse
import com.demo.fred.hackerdemo.net.HackerClient
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Model lass for Detail MVP structure to connect repository layer
 */
class NewsDetailModel @Inject constructor(private val hackerClient: HackerClient) {

    fun getHackNews(id: Int): Observable<NewsResponse> {
        return hackerClient.getHackerNewsItem(id)
    }
}