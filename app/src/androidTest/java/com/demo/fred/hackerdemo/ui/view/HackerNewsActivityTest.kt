package com.demo.fred.hackerdemo.ui.view


import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class HackerNewsActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(HackerNewsActivity::class.java)

    @Test
    fun hackerNewsActivityTest() {
    }
}
