package tk.dsjin.protectednote.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import tk.dsjin.protectednote.usecase.model.NoteModel
import tk.dsjin.protectednote.usecase.repository.NoteRepository
import tk.dsjin.protectednote.usecase.dataholder.Result
import java.util.*

class NoteUseCaseImpl(private val noteRepository: NoteRepository) : NoteUseCase{
    override fun getAllNotes(): Result<LiveData<List<NoteModel>>> {
        return Result.success(
            "",
            Transformations.map(noteRepository.loadAllNotes()) { list ->
                list.map {
                    NoteModel(
                        it.id,
                        it.title,
                        it.detail,
                        it.isProtected,
                        it.encryptedPassword,
                        it.createdAt,
                        it.updatedAt,
                        it.isDeleted,
                        it.deletedAt
                    )
                }
            }
        )
    }

    override fun getNote(id: Int): Result<NoteModel> {
        val note = noteRepository.loadNote(id)
        return if (note != null){
            val convertedNote = NoteModel(
                note.id,
                note.title,
                note.detail,
                note.isProtected,
                note.encryptedPassword,
                note.createdAt,
                note.updatedAt,
                note.isDeleted,
                note.deletedAt
            )
            Result.success(
                "",
                convertedNote
            )
        }else{
            Result.notFound(
                "",
                null
            )
        }
    }

    override suspend fun preDeleteNote(note: NoteModel) : Result<Int> {
        note.apply {
            this.isDeleted = true
            this.deletedAt = Date()
        }
        val result = noteRepository.preDeleteNote(note)
        return if (result > 0){
            Result.success(
                "",
                result
            )
        }else{
             Result.failure(
                "",
                result
            )
        }
    }

    override suspend fun createNote(note: NoteModel) : Result<Long>  {
        val result = noteRepository.createNote(note)
        return if (result > 0){
            Result.success(
                "",
                result
            )
        }else{
            Result.failure(
                "",
                result
            )
        }
    }

    override suspend fun updateNote(note: NoteModel) : Result<Int>  {
        note.apply {
            this.updatedAt = Date()
        }
        val result = noteRepository.updateNote(note)
        return if (result > 0){
            Result.success(
                "",
                result
            )
        }else{
            Result.failure(
                "",
                result
            )
        }
    }

    override suspend fun deleteNote(note: NoteModel) : Result<Int> {
        val result = noteRepository.deleteNote(note.id)
        return if (result > 0){
            Result.success(
                "",
                result
            )
        }else{
            Result.failure(
                "",
                result
            )
        }
    }
}