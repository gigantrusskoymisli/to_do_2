package com.example.to_do_2.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.to_do_2.R
import com.example.to_do_2.viewmodel.NotesViewModel
import kotlinx.android.synthetic.main.fragment_list.view.*

class ListFr : Fragment() {

    private lateinit var mNotesViewModel : NotesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_list, container, false)

        val adapter = ListAdapter()
        val recyclerView = view.recyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mNotesViewModel = ViewModelProvider(this).get(NotesViewModel::class.java)
        mNotesViewModel.readAllData.observe(viewLifecycleOwner, { note ->
            adapter.setData(note)
        })

        view.add.setOnClickListener{
            findNavController().navigate(R.id.action_listFr_to_noteFragment)
        }
        setHasOptionsMenu(true)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_delete -> {
                deleteAllNotes()
            }
            R.id.menu_upload_to -> {
                Toast.makeText(requireContext(), "data uploaded to server",Toast.LENGTH_SHORT).show()
            }
            R.id.menu_upload_from -> {
                Toast.makeText(requireContext(), "data uploaded to app",Toast.LENGTH_SHORT).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllNotes() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Да") { _, _ ->
            mNotesViewModel.deleteAllData()
        }
        builder.setNegativeButton("Нет") {_, _ ->

        }
        builder.setTitle("Удаление заметок")
        builder.setMessage("Вы точно хотите это удалить?")
        builder.create().show()
    }
}