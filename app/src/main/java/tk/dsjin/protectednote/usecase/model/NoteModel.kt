package tk.dsjin.protectednote.usecase.model

import java.util.*


data class NoteModel (
    var id : Int,
    var title : String,
    var detail : String?,
    var isProtected : Boolean,
    val encryptedPassword : String?,
    var createdAt : Date,
    var updatedAt : Date,
    var isDeleted : Boolean,
    var deletedAt : Date?
)