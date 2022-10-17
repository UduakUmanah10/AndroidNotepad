package ps.room.room.database

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [NoteEntity::class], version = 1)

abstract class NotedataBase: RoomDatabase() {

    abstract fun dao():NoteDao

}