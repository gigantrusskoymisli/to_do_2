    package com.example.to_do_2.fragments.templates

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.to_do_2.R
import com.example.to_do_2.viewmodel.TemplatesViewModel
import kotlinx.android.synthetic.main.fragment_template_list.view.*

class TemplateListFragment : Fragment() {

    private lateinit var mTmpViewModel : TemplatesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_template_list, container, false)

        val adapter = TemplateListAdapter()
        val recyclerView = view.templates
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)

        mTmpViewModel = ViewModelProvider(this).get(TemplatesViewModel::class.java)
        mTmpViewModel.readAllTemplates.observe(viewLifecycleOwner, { tmp ->
            adapter.setData(tmp)
        })
        return view
    }

}
