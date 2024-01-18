package com.example.note_takingapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName="note")
@Parcelize
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val notetitle:String,
    val notebody:String

): Parcelable
