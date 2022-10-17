package ps.room.room.database

import androidx.room.*
import ps.room.room.utils.Constants.Note_Table


@Dao
interface NoteDao {

    @Insert
    fun insertNote(noteEntity: NoteEntity)

    @Update
    fun upDateNote(noteEntity: NoteEntity)

    @Delete
    fun deleteNote(noteEntity:NoteEntity)

    @Query("SELECT * FROM $Note_Table ORDER BY noteId DESC")
    fun getAllNotes():MutableList<NoteEntity>


    @Query("SELECT * FROM $Note_Table WHERE noteId LIKE:id")
    fun getNote(id:Int): NoteEntity


}