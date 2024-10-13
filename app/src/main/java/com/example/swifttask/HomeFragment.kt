package com.example.swifttask

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.example.swifttask.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    lateinit var database: NoteDataBase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        database = Room.databaseBuilder(requireActivity(), NoteDataBase::class.java, "Task-DB")
            .allowMainThreadQueries().build()


        val notes: List<Note> = database.getNoteDao().getAllData()

        var adapter = NoteAdapter()
        adapter.submitList(notes)

        binding.recyclerviewTasks.adapter = adapter


        binding.AddTask.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_addListFragment)
        }

        return binding.root
    }

}