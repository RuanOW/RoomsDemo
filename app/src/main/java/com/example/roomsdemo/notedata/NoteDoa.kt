package com.example.roomsdemo.notedata

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDoa {

    @Query("SELECT * from note_table")
    fun getAllNotes():LiveData<List<Note>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: Note)

    @Update
    suspend fun updateNote(note: Note)
}