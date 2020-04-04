package tk.dsjin.protectednote.presenter.notes.datatest

import tk.dsjin.protectednote.usecase.model.NoteModel
import java.util.*

object Note {
    fun getNotes() : List<NoteModel>{
        val date = Date()
        return listOf(
                NoteModel(
                1,
                "Hello From Google",
                "This is detail from google jetpack is so amazing!",
                false,
                null,
                date,
                date,
                false,
                null
            ),
                NoteModel(
                    1,
                    "Hello From Google",
                    "This is detail from google jetpack is so amazing! This is detail from google jetpack is so amazing!",
                    true,
                    null,
                    date,
                    date,
                    false,
                    null
                )
        )
    }
}