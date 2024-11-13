package com.example.demokoch.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mammal")
data class Mammal(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val species: String
)