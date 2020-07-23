package com.example.roomsdemo

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomsdemo.databinding.FragmentNoteListBinding
import com.example.roomsdemo.notedata.Note
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_note_item.*
import kotlinx.coroutines.Job


class NoteListFragment : Fragment() {

    private lateinit var binding: FragmentNoteListBinding
    private val viewModel: NoteViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val noteAdapter = GroupAdapter<GroupieViewHolder>()

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_note_list, container, false)

        binding.addNoteBtn.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_noteListFragment_to_createNoteFragment)
        )

        viewModel.allNote.observe(viewLifecycleOwner, Observer { notes ->
            noteAdapter.update(noteItemList(notes))
        })

        binding.noteListRecylcerView.apply {
            adapter = noteAdapter
            layoutManager = LinearLayoutManager(context)
        }

        noteAdapter.setOnItemClickListener { item, view ->
            val noteItem = item as NoteItem
            Toast.makeText(view.context, "Here is the Title ${noteItem.note.title}", Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }

    private fun noteItemList(notes: List<Note>): MutableCollection<NoteItem> {
        return notes.map {
            NoteItem(it, callChecked)
        } as MutableCollection<NoteItem>
    }

    val callChecked = { note: Note, value: Boolean ->
        note.status = value
        viewModel.updateNote(note)
    }

}

class NoteItem(val note: Note, val action: (note: Note, value: Boolean) -> Job): Item(){

    override fun getLayout() = R.layout.fragment_note_item

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.noteTitle.text = "${note.id} ${note.title}"
        viewHolder.noteStatus.isChecked = note.status
        viewHolder.noteStatus.setOnCheckedChangeListener { compoundButton, b ->
            action(note, b)
        }
    }
}




