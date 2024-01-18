package com.example.note_takingapp

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.room.Query
import androidx.room.util.query
import com.example.note_takingapp.adapters.Noteadaptero
import com.example.note_takingapp.databinding.FragmentHomeFragmentBinding
import com.example.note_takingapp.model.Note
import com.example.note_takingapp.viewmodel.Noteviewmodel


class Home_ragment : Fragment(R.layout.fragment_home_fragment), SearchView.OnQueryTextListener {

    private var _binding: FragmentHomeFragmentBinding? = null
    private val binding get() =_binding!!

    private lateinit var notesviewmodel:Noteviewmodel
    private lateinit var Noteadaptero:Noteadaptero

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       setHasOptionsMenu(true)

        }


   override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
            _binding= FragmentHomeFragmentBinding.inflate(inflater,container,false)
       return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        notesviewmodel=(activity as MainActivity).noteviewmodel
        setuprecyclerview()
        binding.fabaddbtn.setOnClickListener{
            it.findNavController().navigate(R.id.action_home_ragment_to_new_note_Fragment)
    }



}
    fun setuprecyclerview() {
        Noteadaptero= Noteadaptero()

        binding.recyclerView2.apply {
            layoutManager=StaggeredGridLayoutManager(
                2,
                StaggeredGridLayoutManager.VERTICAL
            )
            setHasFixedSize(true)
            adapter=Noteadaptero
        }
        activity?.let {
            notesviewmodel.getallnotes().observe(
                viewLifecycleOwner, {note ->
                Noteadaptero.differ.submitList(note)
                updateUI(note)
            })
        }
    }

    private fun updateUI(note: List<Note>?) {
        if (note != null) {
            if(note.isNotEmpty()) {
                binding.cardview.visibility = View.GONE
                binding.recyclerView2.visibility = View.VISIBLE
            }else{
                binding.cardview.visibility=View.VISIBLE
                binding.recyclerView2.visibility=View.GONE


            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.home_menu,menu)
        val mmenusearch=menu.findItem(R.id.search_menu).actionView as SearchView
        mmenusearch.isSubmitButtonEnabled=false
        mmenusearch.setOnQueryTextListener(this)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        searchnote(query)
        return false
    }

    override fun onQueryTextChange(newtext: String?): Boolean {
        if(newtext!=null){
            searchnote(newtext)
        }
        return true
    }

    private fun searchnote(query: String?) {
        val searchquery="%$query"
        notesviewmodel.searchnotes(searchquery).observe(
            this,
            {list->Noteadaptero.differ.submitList(list)}
        )

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }


}