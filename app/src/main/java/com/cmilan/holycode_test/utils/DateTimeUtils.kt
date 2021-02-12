package com.cmilan.holycode_test.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DateTimeUtils {

    fun formatCommitTime(createdOn: String?): String? {
        return if (createdOn != null && createdOn.isNotEmpty()) {
            val sdfUTC = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
            sdfUTC.timeZone = TimeZone.getTimeZone("UTC")
            try {
                val date = sdfUTC.parse(createdOn)
                val sdfLocale = SimpleDateFormat("dd.MM.yyyy. HH:mm", Locale.getDefault())
                sdfLocale.timeZone = TimeZone.getTimeZone("UTC")
                sdfLocale.format(date ?: Date())
            } catch (e: ParseException) {
                "- -"
            }
        } else {
            "- -"
        }
    }
}