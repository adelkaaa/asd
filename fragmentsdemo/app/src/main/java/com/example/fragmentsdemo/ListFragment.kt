package com.example.fragmentsdemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView

class ListFragment : Fragment() {

    interface OnItemSelectedListener {
        fun onItemSelected(item: String)
    }

    private var listener: OnItemSelectedListener? = null

    fun setOnItemSelectedListener(listener: OnItemSelectedListener) {
        this.listener = listener
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        val listView: ListView = view.findViewById(R.id.listView)
        val items = arrayOf("Кошечка", "Собачка", "Бегемотик", "Ежик")

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, items)
        listView.adapter = adapter

        listView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            listener?.onItemSelected(items[position])
        }

        return view
    }
}
