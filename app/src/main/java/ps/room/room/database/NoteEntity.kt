package ps.room.room.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ps.room.room.utils.Constants.Note_Table

@Entity(tableName = Note_Table)
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val noteId:Int,

    @ColumnInfo(name="note_title")
    val noteTitle:String,

    @ColumnInfo(name="note_Description")
    val noteDescription:String
)
