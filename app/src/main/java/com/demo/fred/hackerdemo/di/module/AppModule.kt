package com.demo.fred.hackerdemo.di.module

import com.demo.fred.hackerdemo.net.HackerClient
import com.demo.fred.hackerdemo.net.HackerService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Module class for application to provide api service and retrofit client
 */
@Module
class AppModule {

    @Singleton
    @Provides
    fun provideHackerNewsService(): HackerService {
        return HackerService.create()
    }

    @Singleton
    @Provides
    fun provideHackerNewsClient(hackerService: HackerService): HackerClient {
        return HackerClient(hackerService)
    }


}