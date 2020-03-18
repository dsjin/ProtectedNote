package tk.dsjin.protectednote.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import tk.dsjin.protectednote.usecase.model.NoteModel
import tk.dsjin.protectednote.usecase.repository.NoteRepository
import java.util.*

class NoteUseCaseImpl(private val noteRepository: NoteRepository) : NoteUseCase{
    override fun getAllNotes(): LiveData<List<NoteModel>> {
        return Transformations.map(noteRepository.loadAllNotes()) { list ->
            list.map {
                NoteModel(
                    it.id,
                    it.title,
                    it.detail,
                    it.isProtected,
                    it.createdAt,
                    it.updatedAt,
                    it.isDeleted,
                    it.deletedAt
                )
            }
        }
    }

    override fun getNote(id: Int): LiveData<NoteModel> {
        return Transformations.map(noteRepository.loadNote(id)) {
            NoteModel(
                it.id,
                it.title,
                it.detail,
                it.isProtected,
                it.createdAt,
                it.updatedAt,
                it.isDeleted,
                it.deletedAt
            )
        }
    }

    override suspend fun preDeleteNote(note: NoteModel) {
        note.apply {
            this.isDeleted = true
            this.deletedAt = Date()
        }
        noteRepository.updateNote(note)
    }

    override suspend fun createNote(note: NoteModel) {
        noteRepository.createNote(note)
    }

    override suspend fun updateNote(note: NoteModel) {
        note.apply {
            this.updatedAt = Date()
        }
        noteRepository.updateNote(note)
    }

    override suspend fun deleteNote(note: NoteModel) {
        noteRepository.deleteNote(note.id)
    }
}