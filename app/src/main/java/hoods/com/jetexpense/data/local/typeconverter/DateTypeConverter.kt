package hoods.com.jetexpense.data.local.typeconverter

import androidx.room.TypeConverter
import java.util.Date

open class DateTypeConverter {
    @TypeConverter
    fun toDate(date: Long?): Date? = date?.let { Date(it) }

    @TypeConverter
    fun fromDate(date: Date?): Long? = date?.time
}