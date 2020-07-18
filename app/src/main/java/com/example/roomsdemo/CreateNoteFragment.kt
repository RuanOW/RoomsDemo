package com.example.roomsdemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.roomsdemo.databinding.FragmentCreateNoteBinding
import com.example.roomsdemo.notedata.Note


class CreateNoteFragment : Fragment() {

    private lateinit var binding: FragmentCreateNoteBinding
    private val viewModel: NoteViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_create_note, container, false)

        binding.createNote.setOnClickListener {
            // Create a new Note object from the fields filled in
            val note = Note(
                0,
                binding.noteTitle.text.toString(),
                binding.noteBody.text.toString(),
                false
            )
            // Insert note
            viewModel.insert(note)
            findNavController().navigate(R.id.action_createNoteFragment_to_noteListFragment)
        }
        return binding.root
    }

}