package com.example.note_takingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ReportFragment.Companion.reportFragment
import androidx.lifecycle.ViewModelProvider
import com.example.note_takingapp.Repository.repository
import com.example.note_takingapp.database.NoteDatabase
import com.example.note_takingapp.databinding.ActivityMainBinding
import com.example.note_takingapp.viewmodel.Noteviewmodel
import com.example.note_takingapp.viewmodel.viewmodelfactory

class MainActivity : AppCompatActivity() {
    lateinit var noteviewmodel:Noteviewmodel
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupviewmodel()
    }

    private fun setupviewmodel() {
        val repository=repository(NoteDatabase(this))
        val viewmodelproviderfactory=viewmodelfactory(application,repository
        )

        noteviewmodel=ViewModelProvider(
            this,
            viewmodelproviderfactory)
            .get(Noteviewmodel::class.java)

    }
}