package com.example.demokoch

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.demokoch.adapter.MammalAdapter
import com.example.demokoch.data.AppDatabase
import com.example.demokoch.data.Mammal
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var database: AppDatabase
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MammalAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        database = AppDatabase.getDatabase(this)

        recyclerView = findViewById(R.id.recyclerView)
        adapter = MammalAdapter(emptyList())
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        val addButton = findViewById<Button>(R.id.buttonAdd)
        val nameEditText = findViewById<EditText>(R.id.editTextName)
        val speciesEditText = findViewById<EditText>(R.id.editTextSpecies)

        addButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val species = speciesEditText.text.toString()

            if (name.isNotEmpty() && species.isNotEmpty()) {
                val mammal = Mammal(name = name, species = species)

                CoroutineScope(Dispatchers.IO).launch {
                    database.mammalDao().insertMammal(mammal)
                    loadMammals()
                }
            }
        }

        CoroutineScope(Dispatchers.IO).launch {
            loadMammals()
        }
    }

    private suspend fun loadMammals() {
        val mammals = database.mammalDao().getAllMammals()
        withContext(Dispatchers.Main) {
            adapter.setMammals(mammals)
        }
    }
}