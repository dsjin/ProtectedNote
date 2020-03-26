package tk.dsjin.protectednote.usecase

import androidx.lifecycle.LiveData
import tk.dsjin.protectednote.usecase.model.NoteModel
import tk.dsjin.protectednote.usecase.dataholder.Result
interface NoteUseCase {
    enum class ResultStatus{
        Success,
        Failure,
        NotFound
    }
    fun getAllNotes() : Result<LiveData<List<NoteModel>>>
    fun getNote(id : Int) : Result<NoteModel>
    suspend fun createNote(note : NoteModel) : Result<Long>
    suspend fun updateNote(note : NoteModel) : Result<Int>
    suspend fun preDeleteNote(note : NoteModel) : Result<Int>
    suspend fun deleteNote(note : NoteModel) : Result<Int>
}