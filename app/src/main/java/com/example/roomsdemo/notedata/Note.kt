package com.example.roomsdemo.notedata

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
data class Note(
    // add default value as 0 for when database is implemented
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var title: String,
    var body: String,
    var status: Boolean
)