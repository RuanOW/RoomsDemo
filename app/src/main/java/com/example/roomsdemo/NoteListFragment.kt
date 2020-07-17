package com.example.roomsdemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomsdemo.databinding.FragmentNoteListBinding
import com.example.roomsdemo.notedata.Note
import com.xwray.groupie.GroupAdapter
//import com.xwray.groupie.GroupieViewHolder
//import com.xwray.groupie.Item
//import com.xwray.groupie.groupiex.plusAssign
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_note_item.*

//import kotlinx.android.synthetic.main.song.*

class NoteListFragment : Fragment() {

    private lateinit var binding: FragmentNoteListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var noteAdapter = GroupAdapter<GroupieViewHolder>()


        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_note_list, container, false)

        binding.addNoteBtn.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_noteListFragment_to_createNoteFragment)
        )
        for(note in dummyList()){
            noteAdapter.add(NoteItem(note))
        }
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

    private fun dummyList(): List<Note>{
        var noteList = mutableListOf<Note>()
        for(i in 1..10){
            noteList.add(Note(i.toLong(), "Note ${i}", "Some body here", false))
        }
        return noteList
    }

}

class NoteItem(val note: Note): Item(){
    override fun getLayout() = R.layout.fragment_note_item

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.noteTitle.text = note.title
        viewHolder.noteStatus.isChecked = note.status
    }

}

//class NoteList(private val noteList: List<Note>): Co
