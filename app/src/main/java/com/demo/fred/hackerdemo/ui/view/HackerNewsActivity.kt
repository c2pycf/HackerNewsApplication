package com.demo.fred.hackerdemo.ui.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.demo.fred.hackerdemo.R
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_hacker_news.*

/**
 * Application main activity,
 * hold the fragment container to transmit fragment
 */
class HackerNewsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_hacker_news)
        setSupportActionBar(toolbar)


    }

    override fun onStart() {
        super.onStart()
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, NewsListFragment())
            .addToBackStack(null)
            .commit()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
