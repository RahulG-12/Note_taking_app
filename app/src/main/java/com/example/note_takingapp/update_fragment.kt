package com.example.note_takingapp

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.note_takingapp.adapters.Noteadaptero
import com.example.note_takingapp.databinding.FragmentNewNoteBinding
import com.example.note_takingapp.databinding.FragmentUpdateFragmentBinding
import com.example.note_takingapp.model.Note
import com.example.note_takingapp.viewmodel.Noteviewmodel


class update_fragment : Fragment(R.layout.fragment_update_fragment) {
    private var _binding: FragmentUpdateFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var notesviewmodel: Noteviewmodel
    private lateinit var currentnote: Note
    private val args: update_fragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUpdateFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        notesviewmodel = (activity as MainActivity).noteviewmodel
        currentnote = args.note!!
        binding.updatenotetitle.setText(currentnote.notetitle)
        binding.typeupdatesomething.setText(currentnote.notebody)

        binding.fabdone.setOnClickListener {
            val title = binding.updatenotetitle.text.toString().trim()
            val body = binding.typeupdatesomething.text.toString().trim()


            if (title.isNotEmpty()) {
                val note = Note(currentnote.id, title, body)
                notesviewmodel.updae(currentnote)
                view.findNavController().navigate(R.id.action_update_fragment_to_home_ragment)
            } else {
                Toast.makeText(
                    context,
                    "please enter details", Toast.LENGTH_LONG
                ).show()
            }
        }


    }
    private fun deletenote(){
        AlertDialog.Builder(activity).apply {
            setTitle("Delete")
            setMessage("Are you sure")
            setPositiveButton("Delete"){_,_->
                notesviewmodel.delete(currentnote)


                view?.findNavController()?.navigate(R.id.action_update_fragment_to_home_ragment)

            }
            setNegativeButton("cancel",null)

        }.create().show()
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        menu.clear()
        inflater.inflate(R.menu.updte_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)

    }
    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_delete->{
                deletenote()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}