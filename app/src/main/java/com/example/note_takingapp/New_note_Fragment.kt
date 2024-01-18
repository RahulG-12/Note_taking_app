package com.example.note_takingapp

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.note_takingapp.adapters.Noteadaptero
import com.example.note_takingapp.databinding.FragmentHomeFragmentBinding
import com.example.note_takingapp.databinding.FragmentNewNoteBinding
import com.example.note_takingapp.model.Note
import com.example.note_takingapp.viewmodel.Noteviewmodel


class New_note_Fragment : Fragment(R.layout.fragment_new_note_) {
    private var _binding: FragmentNewNoteBinding? = null
    private val binding get() = _binding!!

    private lateinit var notesviewmodel: Noteviewmodel
    private lateinit var Noteadaptero: Noteadaptero
    private lateinit var mview: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        notesviewmodel = (activity as MainActivity).noteviewmodel
        mview = view


    }
    private fun insertnote(view: View){
        val newtitle=binding.newnotetitle.text.toString().trim()
        val newbody=binding.typesomething.text.toString().trim()

        if(newtitle.isNotEmpty()){
            val note=Note(0, newtitle, newbody )
            notesviewmodel.addnote(note)

            Toast.makeText(mview.context,
            "you craeted new note"
                ,Toast.LENGTH_LONG).show()
            view.findNavController().navigate(R.id.action_new_note_Fragment_to_home_ragment)
        }else{
            Toast.makeText(mview.context,
            "please enter details",Toast.LENGTH_LONG).show()

        }

    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        menu.clear()
        inflater.inflate(R.menu.new_note_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)

    }
    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_save->{
                insertnote(mview)
            }
        }
        return super.onOptionsItemSelected(item)
    }

}