package hr.santolin.jetpackflowerpower.utils

import androidx.room.TypeConverter
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset

class DateConverter {
    @TypeConverter
    fun fromTimestamp(value: Long?) =
        value?.let {
            LocalDateTime
                .ofInstant(Instant.ofEpochMilli(value), ZoneOffset.UTC)
        }

    @TypeConverter
    fun toTimestamp(value: LocalDateTime?) =
        value?.atZone(ZoneOffset.UTC)
            ?.toInstant()?.toEpochMilli()
//LocalDateTime Java 8 type od date !!
}