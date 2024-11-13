package com.example.fragmentsdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_text, TextFragment())
                .replace(R.id.fragment_container_buttons, ButtonFragment())
                .commit()
        }

        val buttonToSecondActivity: Button = findViewById(R.id.button_to_second_activity)
        val buttonToThirdActivity: Button = findViewById(R.id.button_to_third_activity)

        buttonToSecondActivity.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }

        buttonToThirdActivity.setOnClickListener {
            val intent = Intent(this, ThirdActivity::class.java)
            startActivity(intent)
        }
    }
}
