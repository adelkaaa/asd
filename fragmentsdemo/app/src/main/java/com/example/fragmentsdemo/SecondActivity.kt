package com.example.fragmentsdemo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_second_buttons, ButtonFragment())
                .commit()
        }

        val buttonBackToMain: Button = findViewById(R.id.button_back_to_main)
        buttonBackToMain.setOnClickListener {
            finish()
        }
    }
}
