package com.demo.fred.hackerdemo

import android.app.Activity
import android.app.Application
import android.support.v4.app.Fragment
import com.demo.fred.hackerdemo.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

/**
 * Application class build up app component dagger
 */
class HackerNewsApplication :Application(), HasActivityInjector, HasSupportFragmentInjector{

    @Inject
    lateinit var dispatchingAndroidActivityInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var dispatchingAndroidFragmentInjector: DispatchingAndroidInjector<Fragment>

    @SuppressWarnings("unchecked","unsafe")
    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent.builder()
            .build()
            .inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingAndroidActivityInjector
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return dispatchingAndroidFragmentInjector
    }
}