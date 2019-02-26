package com.demo.fred.hackerdemo

import com.demo.fred.hackerdemo.utils.formatTime
import com.demo.fred.hackerdemo.utils.isLink
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Util extension function unit test
 */
@RunWith(JUnit4::class)
class UtilFunctionTest{

    @Test
    fun formatTimeTest(){
        val timeTest : Long = 1551193680
        val formatTest = formatTime(timeTest)
        Assert.assertEquals("2019-02-27 00:08:00", formatTest)
    }

    @Test
    fun isLinkEXTest(){
        val stringIn = "https"
        Assert.assertTrue(stringIn.isLink())
    }
}