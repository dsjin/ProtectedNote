package tk.dsjin.protectednote.data.repository

import androidx.lifecycle.LiveData
import kotlinx.coroutines.GlobalScope
import tk.dsjin.protectednote.data.model.NoteEntity
import tk.dsjin.protectednote.usecase.model.NoteModel
import tk.dsjin.protectednote.usecase.repository.NoteRepository
import java.util.*

class NoteRepositoryImpl(private val noteDao : NoteDao) : NoteRepository {
    override fun loadAllNotes(): LiveData<List<NoteEntity>> = noteDao.loadAllNotes()

    override fun loadNote(id: Int): LiveData<NoteEntity> = noteDao.loadNote(id)

    override fun loadNotesWithLimit(limit: Int, offset: Int): LiveData<List<NoteEntity>> = noteDao.loadNotesWithLimit(limit, offset)

    override fun loadAllPreDeletedNotes(): LiveData<List<NoteEntity>> = noteDao.loadAllPreDeletedNotes()

    override suspend fun createNote(noteData: NoteModel): Long{
        val mappedNote = NoteEntity().apply {
            this.id = noteData.id
            this.title = noteData.title
            this.detail = noteData.detail
            this.isProtected = noteData.isProtected
        }
        return noteDao.insertNote(mappedNote)
    }

    override suspend fun updateNote(noteData: NoteModel) {
        val mappedNote = NoteEntity().apply {
            this.id = noteData.id
            this.title = noteData.title
            this.detail = noteData.detail
            this.isProtected = noteData.isProtected
            this.createdAt = noteData.createdAt
        }
        noteDao.updateNote(mappedNote)
    }

    override suspend fun preDeleteNote(noteData: NoteModel)  {
        val mappedNote = NoteEntity().apply {
            this.id = noteData.id
            this.title = noteData.title
            this.detail = noteData.detail
            this.isProtected = noteData.isProtected
            this.createdAt = noteData.createdAt
            this.isDeleted = true
            this.deletedAt = Date()
        }
        noteDao.updateNote(mappedNote)
    }

    override suspend fun deleteNote(id : Int) {
        val note = NoteEntity().apply {
            this.id = id
        }
        noteDao.deleteNote(note)
    }
}