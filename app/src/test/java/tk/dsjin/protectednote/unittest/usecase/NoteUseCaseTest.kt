package tk.dsjin.protectednote.unittest.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.any
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import tk.dsjin.protectednote.unittest.util.observeOnce
import tk.dsjin.protectednote.unittest.util.DataHelper
import tk.dsjin.protectednote.usecase.NoteUseCase
import tk.dsjin.protectednote.usecase.NoteUseCaseImpl
import tk.dsjin.protectednote.usecase.repository.NoteRepository
import tk.dsjin.protectednote.usecase.model.NoteModel

@RunWith(MockitoJUnitRunner::class)
class NoteUseCaseTest{
    @get:Rule
    val rule = InstantTaskExecutorRule()
    @Mock
    private lateinit var noteRepoMock : NoteRepository
    private lateinit var SUT : NoteUseCase

    @Before
    fun setUp() {
        SUT = NoteUseCaseImpl(noteRepoMock)
    }

    @Test
    fun noteUseCase_fetchNotes_successStatusReturned() {
        `when`(noteRepoMock.loadAllNotes()).thenReturn(DataHelper.getListNoteLiveData())
        val allNotes = SUT.getAllNotes()
        verify(noteRepoMock).loadAllNotes()
        assertThat(allNotes.result, `is`(NoteUseCase.ResultStatus.Success))
    }

    @Test
    fun noteUseCase_fetchNotes_NoteConvertToNoteModel() {
        `when`(noteRepoMock.loadAllNotes()).thenReturn(DataHelper.getListNoteLiveData())
        val allNotes = SUT.getAllNotes()
        verify(noteRepoMock).loadAllNotes()
        allNotes.data?.observeOnce{ list->
            list.forEach{
                assertThat(it, any(NoteModel::class.java))
            }
        }
    }

    @Test
    fun noteUseCase_fetchNote_successStatusReturned() {
        `when`(noteRepoMock.loadNote(ArgumentMatchers.anyInt())).thenReturn(DataHelper.getNote(DataHelper.CORRECT_ID))
        val note = SUT.getNote(DataHelper.CORRECT_ID)
        verify(noteRepoMock).loadNote(ArgumentMatchers.anyInt())
        assertThat(note.result, `is`(NoteUseCase.ResultStatus.Success))
    }

    @Test
    fun noteUseCase_fetchNote_NoteConvertToNoteModel() {
        `when`(noteRepoMock.loadNote(ArgumentMatchers.anyInt())).thenReturn(DataHelper.getNote(DataHelper.CORRECT_ID))
        val note = SUT.getNote(DataHelper.CORRECT_ID)
        verify(noteRepoMock).loadNote(ArgumentMatchers.anyInt())
        assertThat(note.data, any(NoteModel::class.java))
    }

    @Test
    fun noteUseCase_fetchNote_IncorrectId_noFoundStatusReturn() {
        `when`(noteRepoMock.loadNote(ArgumentMatchers.anyInt())).thenReturn(DataHelper.getNote(DataHelper.INCORRECT_ID))
        val note = SUT.getNote(DataHelper.INCORRECT_ID)
        verify(noteRepoMock).loadNote(ArgumentMatchers.anyInt())
        assertThat(note.result, `is`(NoteUseCase.ResultStatus.NotFound))
    }

    @Test
    fun noteUseCase_createNote_successReturned() = runBlocking{
        `when`(noteRepoMock.createNote(any())).thenReturn(1)
        val createdResult = SUT.createNote(DataHelper.getNoteModel(DataHelper.CORRECT_ID)!!)
        verify(noteRepoMock).createNote(any())
        assertThat(createdResult.result, `is`(NoteUseCase.ResultStatus.Success))
    }

    @Test
    fun noteUseCase_createNote_rowIdReturn() = runBlocking{
        `when`(noteRepoMock.createNote(any())).thenReturn(1)
        val createdResult = SUT.createNote(DataHelper.getNoteModel(DataHelper.CORRECT_ID)!!)
        verify(noteRepoMock).createNote(any())
        assertThat(createdResult.data?.toInt(), `is`(1))
    }

    @Test
    fun noteUseCase_updateNote_successReturned() = runBlocking{
        `when`(noteRepoMock.updateNote(any())).thenReturn(1)
        val updatedResult = SUT.updateNote(DataHelper.getNoteModel(DataHelper.CORRECT_ID)!!)
        verify(noteRepoMock).updateNote(any())
        assertThat(updatedResult.result, `is`(NoteUseCase.ResultStatus.Success))
    }

    @Test
    fun noteUseCase_updateNote_numberOfUpdatedRowReturn() = runBlocking{
        `when`(noteRepoMock.updateNote(any())).thenReturn(1)
        val updatedResult = SUT.updateNote(DataHelper.getNoteModel(DataHelper.CORRECT_ID)!!)
        verify(noteRepoMock).updateNote(any())
        assertThat(updatedResult.data, `is`(1))
    }

    @Test
    fun noteUseCase_updateNote_incorrectId_failureReturn() = runBlocking{
        `when`(noteRepoMock.updateNote(any())).thenReturn(0)
        val updatedResult = SUT.updateNote(DataHelper.getNoteModel(DataHelper.INCORRECT_ID)!!)
        verify(noteRepoMock).updateNote(any())
        assertThat(updatedResult.result, `is`(NoteUseCase.ResultStatus.Failure))
    }

    @Test
    fun noteUseCase_updateNote_incorrectId_zeroReturn() = runBlocking{
        `when`(noteRepoMock.updateNote(any())).thenReturn(0)
        val updatedResult = SUT.updateNote(DataHelper.getNoteModel(DataHelper.INCORRECT_ID)!!)
        verify(noteRepoMock).updateNote(any())
        assertThat(updatedResult.data, `is`(0))
    }

    @Test
    fun noteUseCase_preDeleteNote_successReturned() = runBlocking{
        `when`(noteRepoMock.preDeleteNote(any())).thenReturn(1)
        val preDeletedResult = SUT.preDeleteNote(DataHelper.getNoteModel(DataHelper.CORRECT_ID)!!)
        verify(noteRepoMock).preDeleteNote(any())
        assertThat(preDeletedResult.result, `is`(NoteUseCase.ResultStatus.Success))
    }

    @Test
    fun noteUseCase_preDeleteNote_numberOfUpdatedRowReturn() = runBlocking{
        `when`(noteRepoMock.preDeleteNote(any())).thenReturn(1)
        val preDeletedResult = SUT.preDeleteNote(DataHelper.getNoteModel(DataHelper.CORRECT_ID)!!)
        verify(noteRepoMock).preDeleteNote(any())
        assertThat(preDeletedResult.data, `is`(1))
    }

    @Test
    fun noteUseCase_preDeleteNote_incorrectId_failureReturn() = runBlocking{
        `when`(noteRepoMock.preDeleteNote(any())).thenReturn(0)
        val preDeletedResult = SUT.preDeleteNote(DataHelper.getNoteModel(DataHelper.INCORRECT_ID)!!)
        verify(noteRepoMock).preDeleteNote(any())
        assertThat(preDeletedResult.result, `is`(NoteUseCase.ResultStatus.Failure))
    }

    @Test
    fun noteUseCase_preDeleteNote_incorrectId_zeroReturn() = runBlocking{
        `when`(noteRepoMock.preDeleteNote(any())).thenReturn(0)
        val preDeletedResult = SUT.preDeleteNote(DataHelper.getNoteModel(DataHelper.INCORRECT_ID)!!)
        verify(noteRepoMock).preDeleteNote(any())
        assertThat(preDeletedResult.data, `is`(0))
    }

    @Test
    fun noteUseCase_deleteNote_successReturned() = runBlocking{
        `when`(noteRepoMock.deleteNote(ArgumentMatchers.anyInt())).thenReturn(1)
        val deletedResult = SUT.deleteNote(DataHelper.getNoteModel(DataHelper.CORRECT_ID)!!)
        verify(noteRepoMock).deleteNote(ArgumentMatchers.anyInt())
        assertThat(deletedResult.result, `is`(NoteUseCase.ResultStatus.Success))
    }

    @Test
    fun noteUseCase_deleteNote_numberOfUpdatedRowReturn() = runBlocking{
        `when`(noteRepoMock.deleteNote(ArgumentMatchers.anyInt())).thenReturn(1)
        val deletedResult = SUT.deleteNote(DataHelper.getNoteModel(DataHelper.CORRECT_ID)!!)
        verify(noteRepoMock).deleteNote(ArgumentMatchers.anyInt())
        assertThat(deletedResult.data, `is`(1))
    }

    @Test
    fun noteUseCase_deleteNote_incorrectId_failureReturn() = runBlocking{
        `when`(noteRepoMock.deleteNote(ArgumentMatchers.anyInt())).thenReturn(0)
        val deletedResult = SUT.deleteNote(DataHelper.getNoteModel(DataHelper.INCORRECT_ID)!!)
        verify(noteRepoMock).deleteNote(ArgumentMatchers.anyInt())
        assertThat(deletedResult.result, `is`(NoteUseCase.ResultStatus.Failure))
    }

    @Test
    fun noteUseCase_deleteNote_incorrectId_zeroReturn() = runBlocking{
        `when`(noteRepoMock.deleteNote(ArgumentMatchers.anyInt())).thenReturn(0)
        val deletedResult = SUT.deleteNote(DataHelper.getNoteModel(DataHelper.INCORRECT_ID)!!)
        verify(noteRepoMock).deleteNote(ArgumentMatchers.anyInt())
        assertThat(deletedResult.data, `is`(0))
    }

    /**
     * Returns ArgumentMatchers.any as nullable type to avoid java.lang.IllegalStateException when
     * null is returned.
     */
    private fun <T> any(): T = ArgumentMatchers.any<T>()
}