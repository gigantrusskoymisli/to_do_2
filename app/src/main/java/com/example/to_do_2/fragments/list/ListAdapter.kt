package com.example.to_do_2.fragments.list


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.to_do_2.R
import com.example.to_do_2.model.Notes
import kotlinx.android.synthetic.main.item.view.*

class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var notesList = emptyList<Notes>()

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item, parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = notesList[position]
        holder.itemView.day_tv.text = currentItem.day
        holder.itemView.note_tv.text = currentItem.note

        holder.itemView.item_layout.setOnClickListener {
            val action = ListFrDirections.actionListFrToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    fun setData(notes: List<Notes>){
        this.notesList = notes
        notifyDataSetChanged()
    }
}