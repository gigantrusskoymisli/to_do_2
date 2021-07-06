package com.example.to_do_2.fragments.add

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.to_do_2.R
import com.example.to_do_2.fragments.templates.TemplateListFragment
import com.example.to_do_2.model.Notes
import com.example.to_do_2.model.Templates
import com.example.to_do_2.viewmodel.NotesViewModel
import com.example.to_do_2.viewmodel.TemplatesViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_note.*
import java.text.SimpleDateFormat
import java.util.*


class NoteFragment : Fragment() {

    private lateinit var mNotesViewModel: NotesViewModel
    private lateinit var mTmpViewModel: TemplatesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_note, container, false)

        mNotesViewModel = ViewModelProvider(this).get(NotesViewModel::class.java)
        mTmpViewModel = ViewModelProvider(this).get(TemplatesViewModel::class.java)

        setHasOptionsMenu(true)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.update_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_update -> {
                insertDataToDatabase()
            }
            R.id.menu_use_template -> {
                val fr = TemplateListFragment()
                val transaction = activity?.supportFragmentManager?.beginTransaction()
                transaction?.replace(R.id.fragment_container, fr)
                transaction?.commit()
            }
            R.id.menu_template -> {
                insertTmpToDatabase()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun insertTmpToDatabase() {
        val string = note.text.toString()

        val tmp = Templates(0,string)
        mTmpViewModel.addTemplate(tmp)
    }

    private fun insertDataToDatabase() {
        val sdf = SimpleDateFormat("dd.MM")
        val currentDate = sdf.format(Date())
        val string = note.text.toString()

        val note = Notes(0, currentDate, string)
        mNotesViewModel.addNote(note)

        findNavController().navigate(R.id.action_noteFragment_to_listFr)
    }
}