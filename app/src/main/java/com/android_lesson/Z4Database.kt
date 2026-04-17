package com.android_lesson

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Z4Person::class], version = 1)
abstract class Z4Database : RoomDatabase() {
    abstract fun getPersonDao(): Z4PersonDao

    companion object {
        var INSTANCE: Z4Database? = null

        fun getInstance(context: Context): Z4Database {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE =
                        Room.databaseBuilder(
                            context.applicationContext,
                            Z4Database::class.java,
                            "person.sqlite"
                        ).createFromAsset("person.sqlite").build()
                }
            }
            return INSTANCE!!
        }
    }
}