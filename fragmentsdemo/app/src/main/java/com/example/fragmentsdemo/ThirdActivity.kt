package com.example.fragmentsdemo

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class ThirdActivity : AppCompatActivity(), ListFragment.OnItemSelectedListener {

    private lateinit var detailFragment: DetailFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        if (savedInstanceState == null) {
            val listFragment = ListFragment()
            listFragment.setOnItemSelectedListener(this)
            detailFragment = DetailFragment()

            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_list, listFragment)
                .replace(R.id.fragment_container_detail, detailFragment)
                .commit()
        }

        val buttonBackToMain: Button = findViewById(R.id.button_back_to_main)
        buttonBackToMain.setOnClickListener {
            finish()
        }
    }

    override fun onItemSelected(item: String) {
        detailFragment.updateDetails(item)
    }
}
