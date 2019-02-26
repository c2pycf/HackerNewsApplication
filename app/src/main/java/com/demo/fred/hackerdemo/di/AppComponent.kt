package com.demo.fred.hackerdemo.di

import com.demo.fred.hackerdemo.HackerNewsApplication
import com.demo.fred.hackerdemo.di.module.AbstractInjectionModule
import com.demo.fred.hackerdemo.di.module.AppModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * Component class for dagger injection
 */
@Singleton
@Component(
    modules = [AndroidInjectionModule::class,
        AbstractInjectionModule::class,
        AppModule::class]
)
interface AppComponent {
    fun inject(target: HackerNewsApplication)
}