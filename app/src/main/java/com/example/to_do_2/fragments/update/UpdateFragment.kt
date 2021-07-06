package com.example.to_do_2.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.to_do_2.R
import com.example.to_do_2.model.Notes
import com.example.to_do_2.viewmodel.NotesViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*

class UpdateFragment : Fragment() {

    private lateinit var mNotesViewModel : NotesViewModel
    
    private val args by navArgs<UpdateFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        mNotesViewModel = ViewModelProvider(this).get(NotesViewModel::class.java)

        view.upd_note.setText(args.currentNote.note)
        view.upd_day.text = args.currentNote.day

        setHasOptionsMenu(true)

        context?.let {
            view.setOnTouchListener(object : OnSwipeTouchListener(it) {

            override fun onSwipeTop() {

                findNavController().navigate(R.id.action_updateFragment_to_listFr)
                super.onSwipeTop()
            }

            override fun onSwipeLeft() {

                Toast.makeText(requireContext(),"left",Toast.LENGTH_SHORT).show()
                super.onSwipeLeft()
            }

            override fun onSwipeRight() {

                Toast.makeText(requireContext(),"right",Toast.LENGTH_SHORT).show()
                super.onSwipeRight()
            }
        })
        }

        return view
    }

    private fun updateItem() {
        val note = upd_note.text.toString()
        val day = upd_day.text.toString()

        val updateNote = Notes(args.currentNote.id, day, note)

        mNotesViewModel.updateNote(updateNote)
        findNavController().navigate(R.id.action_updateFragment_to_listFr)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.upd_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete) {
            deleteNote()
        }
        if(item.itemId == R.id.menu_update) {
            updateItem()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteNote() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Да") { _, _ ->
            mNotesViewModel.deleteNote(args.currentNote)
            findNavController().navigate(R.id.action_updateFragment_to_listFr)
        }
        builder.setNegativeButton("Нет") {_, _ ->

        }
        builder.setTitle("Удаление заметки")
        builder.setMessage("Вы точно хотите это удалить?")
        builder.create().show()
    }
}