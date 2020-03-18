package tk.dsjin.protectednote.data.util

import tk.dsjin.protectednote.usecase.model.NoteModel
import java.util.*

class DataHelper {
    companion object{
        fun getNote() : NoteModel {
            val nowDate = Date()
            return NoteModel(
                0,
                "Title",
                "Detail",
                false,
                nowDate,
                nowDate,
                false,
                null
            )
        }
    }
}