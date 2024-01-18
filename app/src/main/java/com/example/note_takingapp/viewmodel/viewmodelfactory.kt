package com.example.note_takingapp.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.note_takingapp.Repository.repository

class viewmodelfactory(
    val app: Application,
    private  val repository:repository
):ViewModelProvider.Factory
{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return Noteviewmodel (app,repository) as T
    }
}