package tk.dsjin.protectednote.usecase.repository

import androidx.lifecycle.LiveData
import tk.dsjin.protectednote.data.model.NoteEntity
import tk.dsjin.protectednote.usecase.model.NoteModel

interface NoteRepository{
    fun loadAllNotes() : LiveData<List<NoteEntity>>
    fun loadNote(id : Int) : LiveData<NoteEntity>
    fun loadNotesWithLimit(limit : Int, offset : Int) : LiveData<List<NoteEntity>>
    fun loadAllPreDeletedNotes() : LiveData<List<NoteEntity>>
    suspend fun createNote(noteData : NoteModel) : Long
    suspend fun updateNote(noteData : NoteModel)
    suspend fun preDeleteNote(noteData : NoteModel)
    suspend fun deleteNote(id : Int)
}