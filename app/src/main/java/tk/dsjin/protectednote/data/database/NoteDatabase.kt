package tk.dsjin.protectednote.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import tk.dsjin.protectednote.data.converter.DateConverter
import tk.dsjin.protectednote.data.model.NoteEntity
import tk.dsjin.protectednote.data.repository.NoteDao

@Database(entities = arrayOf(NoteEntity::class), version = 1)
@TypeConverters(DateConverter::class)
abstract class NoteDatabase : RoomDatabase(){
    abstract fun getNoteDao() : NoteDao
}