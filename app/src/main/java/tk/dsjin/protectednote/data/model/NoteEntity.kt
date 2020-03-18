package tk.dsjin.protectednote.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "notes")
data class NoteEntity (
    @PrimaryKey(autoGenerate = true) var id : Int = 0,
    @ColumnInfo(name = "title") var title : String = "",
    @ColumnInfo(name = "detail") var detail : String? = "",
    @ColumnInfo(name = "is_protected") var isProtected : Boolean = false,
    @ColumnInfo(name = "encrypted_password") var encryptedPassword : String? = "",
    @ColumnInfo(name = "created_at") var createdAt : Date = Date(),
    @ColumnInfo(name = "updated_at") var updatedAt : Date = Date(),
    @ColumnInfo(name = "is_deleted") var isDeleted : Boolean = false,
    @ColumnInfo(name = "deleted_at") var deletedAt : Date? = null
){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as NoteEntity

        if (id != other.id) return false
        if (title != other.title) return false
        if (detail != other.detail) return false
        if (isProtected != other.isProtected) return false
        if (createdAt != other.createdAt) return false
        if (updatedAt != other.updatedAt) return false
        if (isDeleted != other.isDeleted) return false
        if (deletedAt != other.deletedAt) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + title.hashCode()
        result = 31 * result + (detail?.hashCode() ?: 0)
        result = 31 * result + isProtected.hashCode()
        result = 31 * result + createdAt.hashCode()
        result = 31 * result + updatedAt.hashCode()
        result = 31 * result + isDeleted.hashCode()
        result = 31 * result + (deletedAt?.hashCode() ?: 0)
        return result
    }
}