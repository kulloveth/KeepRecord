package com.developer.kulloveth.keeprecord

import java.text.SimpleDateFormat
import java.util.*

object DateHelper {

    fun formatDate(calendar: Calendar): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return dateFormat.format(calendar.time)
    }
}