package com.example.fragmentsdemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast

class ButtonFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_button, container, false)

        val button1: Button = view.findViewById(R.id.button1)
        val button2: Button = view.findViewById(R.id.button2)

        button1.setOnClickListener {
            Toast.makeText(activity, "Button 1 clicked", Toast.LENGTH_SHORT).show()
        }

        button2.setOnClickListener {
            Toast.makeText(activity, "Button 2 clicked", Toast.LENGTH_SHORT).show()
        }

        return view
    }
}
