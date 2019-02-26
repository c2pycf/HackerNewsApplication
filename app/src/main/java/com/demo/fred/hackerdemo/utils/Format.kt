package com.demo.fred.hackerdemo.utils

import android.os.Build
import android.text.Html
import java.text.SimpleDateFormat
import java.util.*

/**
 * Extenstion class to format time and HTML text
 */
fun formatTime(time: Long): String {
    val formatter = SimpleDateFormat(Constants.SIMPLE_FORMAT, Locale.US)
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = time
    return formatter.format(calendar.time)
}

@SuppressWarnings("deprecation")
fun formatHTMLText(text: String): String {
    return if (Build.VERSION.SDK_INT >= 24) {
        Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY).toString()
    } else {
        Html.fromHtml(text).toString()
    }
}
