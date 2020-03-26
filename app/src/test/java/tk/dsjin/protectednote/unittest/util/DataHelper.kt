package tk.dsjin.protectednote.unittest.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import tk.dsjin.protectednote.data.model.NoteEntity
import tk.dsjin.protectednote.usecase.model.NoteModel
import java.util.*
import kotlin.collections.ArrayList

class DataHelper {
    companion object{
        const val CORRECT_ID = 1
        const val INCORRECT_ID = 999
        fun getListNoteLiveData() : LiveData<List<NoteEntity>> {
            val nowDate = Date()
            val noteList = ArrayList<NoteEntity>()
            noteList.add(
                    NoteEntity(
                    0,
                    "Title",
                    "Detail",
                    false,
                    null,
                    nowDate,
                    nowDate,
                    false,
                    null
                )
            )
            return MutableLiveData<List<NoteEntity>>().apply {
                this.value = noteList
            }
        }
        fun getNote(id : Int) : NoteEntity? {
            val nowDate = Date()
            val noteList = ArrayList<NoteEntity>()
            noteList.add(
                NoteEntity(
                    0,
                    "Title",
                    "Detail",
                    false,
                    null,
                    nowDate,
                    nowDate,
                    false,
                    null
                )
            )
            noteList.add(
                NoteEntity(
                    1,
                    "Title",
                    "Detail",
                    false,
                    null,
                    nowDate,
                    nowDate,
                    false,
                    null
                )
            )
            val selectedNote = noteList.filter {
                it.id == id
            }
            return if (selectedNote.isNotEmpty()) {
                selectedNote[0]
            } else {
                null
            }
        }
        fun getNoteModel(id : Int) : NoteModel? {
            val nowDate = Date()
            val noteList = ArrayList<NoteModel>()
            noteList.add(
                NoteModel(
                    0,
                    "Title",
                    "Detail",
                    false,
                    "",
                    nowDate,
                    nowDate,
                    false,
                    null
                )
            )
            noteList.add(
                NoteModel(
                    1,
                    "Title",
                    "Detail",
                    false,
                    "",
                    nowDate,
                    nowDate,
                    false,
                    null
                )
            )
            val selectedNote = noteList.filter {
                it.id == id
            }
            return if (selectedNote.isNotEmpty()) {
                selectedNote[0]
            } else {
                NoteModel(
                    0,
                    "Title",
                    "Detail",
                    false,
                    "",
                    nowDate,
                    nowDate,
                    false,
                    null
                )
            }
        }
    }
}