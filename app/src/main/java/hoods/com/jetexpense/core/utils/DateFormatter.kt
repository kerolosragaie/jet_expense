package hoods.com.jetexpense.core.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

object DateFormatter{

     fun formatDate(
        date: Calendar,
    ): String {
        val c = date
        val randomDay = 1..7
        c.set(Calendar.DAY_OF_WEEK, randomDay.random())
        return SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            .format(c.time)
    }

    fun formatDate(date: Date): String =
        SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            .format(date)

    fun formatDays(days: Int) = when (days) {
        1 -> "Mon"
        2 -> "Tue"
        3 -> "Wed"
        4 -> "thus"
        5 -> "Fri"
        6 -> "Sat"
        7 -> "Sun"
        else -> "Unknown"
    }

    fun formatDays(date: Date): String {
        val sdf = SimpleDateFormat("EE", Locale.getDefault())
        return sdf.format(date)
    }
}