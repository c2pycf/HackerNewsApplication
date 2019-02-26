package com.demo.fred.hackerdemo.net

import com.demo.fred.hackerdemo.model.NewsResponse
import io.reactivex.Observable

/**
 * Network client class
 */
class HackerClient(private val hackerService: HackerService) {

    fun getHackerTopNews(): Observable<List<Int>> {
        return hackerService.getHackerTopStory()
    }

    fun getHackerNewsItem(id: Int): Observable<NewsResponse> {
        return hackerService.getHackNews(id)
    }
}