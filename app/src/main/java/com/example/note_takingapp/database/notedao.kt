package com.example.note_takingapp.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.note_takingapp.model.Note

@Dao
interface notedao {
    @Insert(onConflict =OnConflictStrategy.REPLACE)
    suspend fun insert(note:Note)
    @Update
    suspend fun update(note:Note)

     @Delete
    suspend fun delete(note:Note)


    @Query("SELECT * FROM note ORDER BY ID DESC")
    fun getallnotes(): LiveData<List<Note>>

    @Query("SELECT * FROM note WHERE notetitle LIKE:query OR notebody LIKE:query")
    fun searchnotes(query: String?): LiveData<List<Note>>





}