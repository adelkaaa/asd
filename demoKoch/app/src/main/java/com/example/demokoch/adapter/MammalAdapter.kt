package com.example.demokoch.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.demokoch.data.Mammal
import com.example.demokoch.R

class MammalAdapter(private var mammals: List<Mammal>) :
    RecyclerView.Adapter<MammalAdapter.MammalViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MammalViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_list_item_2, parent, false)
        return MammalViewHolder(view)
    }

    override fun onBindViewHolder(holder: MammalViewHolder, position: Int) {
        val mammal = mammals[position]
        holder.nameTextView.text = mammal.name
        holder.speciesTextView.text = mammal.species
    }

    override fun getItemCount(): Int = mammals.size

    fun setMammals(mammals: List<Mammal>) {
        this.mammals = mammals
        notifyDataSetChanged()
    }

    class MammalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(android.R.id.text1)
        val speciesTextView: TextView = itemView.findViewById(android.R.id.text2)
    }
}