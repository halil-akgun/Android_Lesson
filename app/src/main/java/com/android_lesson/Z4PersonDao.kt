package com.android_lesson

import androidx.room.Dao
import androidx.room.Query

@Dao
interface Z4PersonDao {
    @Query("SELECT * FROM person")
    suspend fun getAll(): List<Z4Person>
}