package com.example.roomsdemo.notedata

data class Note(
    // add default value as 0 for when database is implemented
    var id: Long = 0,
    var title: String,
    var body: String,
    var status: Boolean
)