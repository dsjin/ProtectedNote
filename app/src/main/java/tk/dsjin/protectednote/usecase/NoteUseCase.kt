package tk.dsjin.protectednote.usecase

import androidx.lifecycle.LiveData
import tk.dsjin.protectednote.usecase.model.NoteModel

interface NoteUseCase {
    fun getAllNotes() : LiveData<List<NoteModel>>
    fun getNote(id : Int) : LiveData<NoteModel>
    suspend fun createNote(note : NoteModel)
    suspend fun updateNote(note : NoteModel)
    suspend fun preDeleteNote(note : NoteModel)
    suspend fun deleteNote(note : NoteModel)
}