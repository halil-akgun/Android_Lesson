package com.android_lesson

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class db_Helper(context: Context) : SQLiteOpenHelper(context, "person.sqlite", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(
            "CREATE TABLE person(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, tel TEXT" +
                    ", age INTEGER, height DOUBLE);"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS person;")
        onCreate(db)
    }

}