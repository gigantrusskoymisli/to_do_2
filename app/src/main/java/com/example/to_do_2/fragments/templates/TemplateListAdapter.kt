package com.example.to_do_2.fragments.templates


import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.to_do_2.MainActivity
import com.example.to_do_2.R
import com.example.to_do_2.fragments.add.NoteFragment
import com.example.to_do_2.fragments.add.NoteFragmentDirections
import com.example.to_do_2.model.Templates
import kotlinx.android.synthetic.main.item.view.*
import kotlinx.android.synthetic.main.template_item.view.*
import java.util.*
import kotlin.coroutines.coroutineContext

class TemplateListAdapter : RecyclerView.Adapter<TemplateListAdapter.TempViewHolder>() {

    private var tmpList = emptyList<Templates>()

    class TempViewHolder(itemView : View): RecyclerView.ViewHolder(itemView) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TempViewHolder {
        return TempViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.template_item, parent, false))
    }

    override fun onBindViewHolder(holder: TempViewHolder, position: Int) {
        val currentItem = tmpList[position]
        holder.itemView.tmp_name.text = currentItem.id.toString()
        holder.itemView.tmp_note.text = currentItem.tmpNote

        holder.itemView.tmp_item_layout.setOnClickListener(){
            Toast.makeText(holder.itemView.context, "pressed ${currentItem.id}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return tmpList.size
    }

    fun setData(tmp: List<Templates>){
        this.tmpList = tmp
        notifyDataSetChanged()
    }

    fun removeFromFragment() {
    }

}