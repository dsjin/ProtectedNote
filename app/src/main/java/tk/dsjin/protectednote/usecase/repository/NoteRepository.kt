package tk.dsjin.protectednote.usecase.repository

import androidx.lifecycle.LiveData
import tk.dsjin.protectednote.data.model.NoteEntity
import tk.dsjin.protectednote.usecase.model.NoteModel

interface NoteRepository{
    fun loadAllNotes() : LiveData<List<NoteEntity>>
    fun loadNote(id : Int) : NoteEntity
    fun loadNotesWithLimit(limit : Int, offset : Int) : LiveData<List<NoteEntity>>
    fun loadAllPreDeletedNotes() : LiveData<List<NoteEntity>>
    suspend fun createNote(noteData : NoteModel) : Long
    suspend fun updateNote(noteData : NoteModel) : Int
    suspend fun preDeleteNote(noteData : NoteModel) : Int
    suspend fun deleteNote(id : Int) : Int
}