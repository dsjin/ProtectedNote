package tk.dsjin.protectednote.data.database

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import tk.dsjin.protectednote.data.repository.NoteDao
import tk.dsjin.protectednote.data.repository.NoteRepositoryImpl
import tk.dsjin.protectednote.data.util.DataHelper
import tk.dsjin.protectednote.data.util.observeOnce
import tk.dsjin.protectednote.usecase.model.NoteModel
import tk.dsjin.protectednote.usecase.repository.NoteRepository
import java.io.IOException
import java.lang.Exception

@RunWith(AndroidJUnit4::class)
class NoteDatabaseTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var noteDao : NoteDao
    private lateinit var db : NoteDatabase
    private lateinit var noteRepo : NoteRepository

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context,
            NoteDatabase::class.java
        ).build()
        noteDao = db.getNoteDao()
        noteRepo = NoteRepositoryImpl(noteDao)
    }

    @After
    @Throws(IOException::class)
    fun tearDown() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun noteDatabase_writeNewNote_noteAddToDatabase() = runBlocking{
        val newNote = addNote()
        val getNote = noteRepo.loadNote(newNote.id)
        assertThat(getNote.id, equalTo(newNote.id))
    }

    @Test
    fun noteDatabase_updateNode_noteUpdatedToDatabase() = runBlocking{
        val newNote = addNote()
        newNote.apply {
            this.detail = "Change To Another Details"
        }
        noteRepo.updateNote(newNote)
        val getNote = noteRepo.loadNote(newNote.id)
        assertThat(newNote.id, equalTo(getNote.id))
    }

    @Test
    fun noteDatabase_preDeleteNote_notePreDeletedInDatabase() = runBlocking{
        val newNote = addNote()
        newNote.apply {
            this.isDeleted = true
        }
        noteRepo.preDeleteNote(newNote)
        val getNote = noteRepo.loadNote(newNote.id)
        assertThat(newNote.isDeleted, equalTo(getNote.isDeleted))
    }

    @Test
    fun noteDatabase_deleteNote_noteDeletedInDatabase() = runBlocking{
        val newNote = addNote()
        noteRepo.deleteNote(newNote.id)
        val getNote = noteRepo.loadNote(newNote.id)
        assertThat(getNote, `is`(nullValue()))
    }

    @Test
    fun noteDatabase_getAllDatabase_returnListOfNote() = runBlocking{
        addNote()
        addNote()
        addNote()
        val getNotes = noteRepo.loadAllNotes()
        getNotes.observeOnce {
            assertThat(it.size, equalTo(3))
        }
    }

    private suspend fun addNote() : NoteModel {
        val note = DataHelper.getNote()
        val id = noteRepo.createNote(note)
        note.apply {
            this.id = id.toInt()
        }
        return note
    }

}
