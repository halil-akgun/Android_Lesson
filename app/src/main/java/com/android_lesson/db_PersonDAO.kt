package com.android_lesson

import android.content.ContentValues

class db_PersonDAO {

    fun save(dbHelper: db_Helper, name: String, tel: String, age: Int, height: Double) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("name", name)
            put("tel", tel)
            put("age", age)
            put("height", height)
        }
        db.insert("person", null, values)
        db.close()
    }
}