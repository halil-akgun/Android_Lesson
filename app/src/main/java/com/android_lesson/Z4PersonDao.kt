package com.android_lesson

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface Z4PersonDao {
    @Query("SELECT * FROM person")
    suspend fun getAll(): List<Z4Person>

    @Insert
    suspend fun insert(person: Z4Person)

    @Update
    suspend fun update(person: Z4Person)

    @Delete
    suspend fun delete(person: Z4Person)

    @Query("SELECT * FROM person WHERE id = :id")
    suspend fun getPerson(id: Int): Z4Person

    @Query("SELECT * FROM person WHERE name = :keyword OR age = :keyword")
    suspend fun filter(keyword: String): List<Z4Person>
}