package tk.dsjin.protectednote.data.repository

import androidx.lifecycle.LiveData
import androidx.room.*
import tk.dsjin.protectednote.data.model.NoteEntity
import tk.dsjin.protectednote.usecase.model.NoteModel
import tk.dsjin.protectednote.usecase.repository.NoteRepository

@Dao
interface NoteDao {
    @Query(value = "select * from NOTES")
    fun loadAllNotes(): LiveData<List<NoteEntity>>

    @Query(value = "select * from notes where id = :id")
    fun loadNote(id: Int): LiveData<NoteEntity>

    @Query(value = "select * from notes limit :limit offset :offset")
    fun loadNotesWithLimit(limit: Int, offset : Int): LiveData<List<NoteEntity>>

    @Query(value = "select * from notes where is_deleted = 1")
    fun loadAllPreDeletedNotes(): LiveData<List<NoteEntity>>
    
    @Insert
    suspend fun insertNote(noteData: NoteEntity) : Long

    @Update
    suspend fun updateNote(noteData: NoteEntity)

    @Delete
    suspend fun deleteNote(noteData: NoteEntity)
}