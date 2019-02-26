package com.demo.fred.hackerdemo.di.module

import com.demo.fred.hackerdemo.ui.view.HackerNewsActivity
import com.demo.fred.hackerdemo.ui.view.NewsDetailFragment
import com.demo.fred.hackerdemo.ui.view.NewsListFragment
import com.demo.fred.hackerdemo.ui.view.NewsWebViewFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Module class for AbstractInjection
 *Provide Android component for dependency
 */
@Suppress("unused")
@Module
abstract class AbstractInjectionModule {
    @ContributesAndroidInjector
    abstract fun contributesHackerNewsActivity(): HackerNewsActivity

    @ContributesAndroidInjector
    abstract fun contributesNewsListFragment(): NewsListFragment

    @ContributesAndroidInjector
    abstract fun contributesNewsDetailFragment(): NewsDetailFragment

    @ContributesAndroidInjector
    abstract fun contributesNewsWebViewFragment(): NewsWebViewFragment

}