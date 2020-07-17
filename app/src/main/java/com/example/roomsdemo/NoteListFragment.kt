package com.example.roomsdemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.roomsdemo.databinding.FragmentNoteItemBinding
import com.example.roomsdemo.databinding.FragmentNoteListBinding
import kotlinx.android.synthetic.main.fragment_note_list.*

class NoteListFragment : Fragment() {

    private lateinit var binding: FragmentNoteListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_note_list, container, false)

        binding.addNoteBtn.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_noteListFragment_to_createNoteFragment)
        )

        return binding.root
    }


}