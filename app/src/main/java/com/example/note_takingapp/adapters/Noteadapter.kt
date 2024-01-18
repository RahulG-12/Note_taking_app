package com.example.note_takingapp.adapters

import android.graphics.Color
import android.graphics.Color.argb
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.note_takingapp.Home_ragment
import com.example.note_takingapp.Home_ragmentDirections
import com.example.note_takingapp.databinding.NoteBinding
import com.example.note_takingapp.model.Note
import com.example.note_takingapp.viewmodel.Noteviewmodel
import java.util.*
import kotlin.random.Random.Default.nextInt

class Noteadaptero:RecyclerView.Adapter<Noteadaptero.noteviewmodel>() {
    class noteviewmodel(val itembinding:NoteBinding):
            RecyclerView.ViewHolder(itembinding.root)
    
    
    private val differback=object:DiffUtil.ItemCallback<Note>(){
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id==newItem.id &&
                    oldItem.notetitle==newItem.notetitle&&
            oldItem.notebody==newItem.notebody
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem==newItem
        }


        
    }
    val differ=AsyncListDiffer(this,differback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): noteviewmodel {
        return noteviewmodel(
            NoteBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun onBindViewHolder(holder: noteviewmodel, position: Int) {
        val currentnote=differ.currentList[position]
        holder.itembinding.notetitle.text=currentnote.notetitle
        holder.itembinding.typesomethingnote.text=currentnote.notebody

        val random=Random( )
        val color=Color.argb(255,
            random.nextInt(256),
            random.nextInt(256),
            random.nextInt(256))


        holder.itembinding.ibColor.setBackgroundColor(color)
        holder.itemView.setOnClickListener{
            val direction=Home_ragmentDirections.actionHomeRagmentToUpdateFragment(currentnote)
            it.findNavController().navigate(direction)

        }


    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    }
