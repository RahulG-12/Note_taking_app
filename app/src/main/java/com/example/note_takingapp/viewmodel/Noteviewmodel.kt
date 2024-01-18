package com.example.note_takingapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.viewModelScope
import androidx.room.Query
import com.example.note_takingapp.Repository.repository
import com.example.note_takingapp.model.Note
import kotlinx.coroutines.launch

class Noteviewmodel (
    app: Application,
    private val repository:repository):
    AndroidViewModel(app)


{
   fun addnote(note: Note)=
       viewModelScope.launch {
           repository.insert(note)
       }


           fun delete(note: Note)=
               viewModelScope.launch {
                   repository.delete(note)
               }

    fun updae(note: Note)=
        viewModelScope.launch {
            repository.update(note)
        }


      fun getallnotes()=repository.getallnotes()
      fun searchnotes(query: String?)=repository.searchnotes(query)

}