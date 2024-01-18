package com.example.note_takingapp.Repository

import com.example.note_takingapp.database.NoteDatabase
import com.example.note_takingapp.model.Note

class repository(private val db:NoteDatabase) {

    suspend fun insert(note: Note)=db.getnotedao().insert(note)
    suspend fun update(note: Note)=db.getnotedao().update(note)

    suspend fun delete(note: Note)=db.getnotedao().delete(note)

    fun getallnotes()=db.getnotedao().getallnotes()
    fun searchnotes(query: String?)=db.getnotedao().searchnotes(query)
}