package com.demo.fred.hackerdemo.net

import com.demo.fred.hackerdemo.model.NewsResponse
import com.demo.fred.hackerdemo.utils.Constants.Companion.BASE_URL_HACKER
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Hacker news api service for handling network request
 * to Hacker news server
 */
interface HackerService {

    @GET("v0/topstories.json")
    fun getHackerTopStory(): Observable<List<Int>>

    @GET("v0/item/{id}.json")
    fun getHackNews(@Path("id") id: Int): Observable<NewsResponse>

    companion object Creator {
        fun create(): HackerService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL_HACKER)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(HackerService::class.java)
        }
    }
}