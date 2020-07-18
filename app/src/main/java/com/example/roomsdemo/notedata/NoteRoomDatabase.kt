package com.example.roomsdemo.notedata

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Note::class), version = 1, exportSchema = false)
public abstract class NoteRoomDatabase: RoomDatabase() {

    abstract fun noteDoa(): NoteDoa

    companion object {
        // Singleton prevent multiple instances of database opening at the same time
        @Volatile
        private var INSTANCE: NoteRoomDatabase? = null

        fun getDatabase(context: Context): NoteRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteRoomDatabase::class.java,
                    "note_table"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}