package com.example.roomsdemo.notedata

import androidx.lifecycle.LiveData

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class NoteRepository(private val noteDoa: NoteDoa) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allNotes: LiveData<List<Note>> = noteDoa.getAllNotes()

    suspend fun insert(note: Note) {
        noteDoa.insert(note)
    }

    suspend fun updateNote(note: Note) {
        noteDoa.updateNote(note)
    }
}