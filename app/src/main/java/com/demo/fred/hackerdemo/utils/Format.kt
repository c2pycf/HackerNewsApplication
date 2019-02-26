package com.demo.fred.hackerdemo.utils

import android.os.Build
import android.text.Html
import com.demo.fred.hackerdemo.utils.Constants.Companion.SIMPLE_FORMAT
import com.demo.fred.hackerdemo.utils.Constants.Companion.URL_PREFIX
import java.text.SimpleDateFormat
import java.util.*

/**
 * Extenstion class to format time and HTML text
 */
fun formatTime(time: Long): String {
    val formatter = SimpleDateFormat(SIMPLE_FORMAT, Locale.getDefault())
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = time * 1000
    return formatter.format(calendar.time)
}

@SuppressWarnings("deprecation")
fun formatHTMLText(text: String?): String {
    when (text != null) {

        true -> return if (Build.VERSION.SDK_INT >= 24) {
            Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY).toString()
        } else {
            Html.fromHtml(text).toString()
        }
    }
    return " "
}

fun String.isLink(): Boolean {
    return this.startsWith(URL_PREFIX)
}
