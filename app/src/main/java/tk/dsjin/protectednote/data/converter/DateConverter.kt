package tk.dsjin.protectednote.data.converter

import androidx.room.TypeConverter
import java.util.*

class DateConverter {
    @TypeConverter
    fun timestampToDate(timestamp : Long?) : Date? {
        return timestamp?.let {
            Date(it)
        }
    }

    @TypeConverter
    fun dateToTimestamp(date : Date?) : Long?{
        return date?.time
    }
}