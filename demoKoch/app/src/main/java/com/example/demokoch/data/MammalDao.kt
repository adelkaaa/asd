package com.example.demokoch.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MammalDao {

    // Асинхронная вставка млекопитающего
    @Insert
    suspend fun insertMammal(mammal: Mammal): Long  // Возвращает ID вставленной записи

    // Асинхронное удаление млекопитающего
    @Delete
    suspend fun deleteMammal(mammal: Mammal): Int  // Возвращает количество удаленных строк

    // Асинхронное получение всех млекопитающих
    @Query("SELECT * FROM mammal")
    suspend fun getAllMammals(): List<Mammal>  // Возвращает список млекопитающих
}