package com.example.fragmentsdemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class DetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail, container, false)
        return view
    }

    fun updateDetails(item: String) {
        val detailTextView: TextView? = view?.findViewById(R.id.detailTextView)
        detailTextView?.text = "Детали для $item : милый(ая)"
    }
}
