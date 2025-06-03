package com.android_lesson

import android.content.ContentValues

class DbPersonDAO {

    fun save(dbHelper: DbHelper, name: String, tel: String, age: Int, height: Double) {
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
    fun update(dbHelper: DbHelper, id: Int, name: String, tel: String, age: Int, height: Double) {
        val db = dbHelper.writableDatabase
        val values = ContentValues()
        values.put("name", name)
        values.put("tel", tel)
        values.put("age", age)
        values.put("height", height)
        db.update("person", values, "id=?", arrayOf(id.toString()))
    }

    fun delete(dbHelper: DbHelper, id: Int) {
        val db = dbHelper.writableDatabase
        db.delete("person", "id=?", arrayOf(id.toString()))
    }

    fun getAll(dbHelper: DbHelper): List<DbPerson> {
        val list = mutableListOf<DbPerson>()
        val db = dbHelper.writableDatabase
        val cursor = db.rawQuery("SELECT * FROM person", null)
        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
            val name = cursor.getString(cursor.getColumnIndexOrThrow("name"))
            val tel = cursor.getString(cursor.getColumnIndexOrThrow("tel"))
            val age = cursor.getInt(cursor.getColumnIndexOrThrow("age"))
            val height = cursor.getDouble(cursor.getColumnIndexOrThrow("height"))
            list.add(DbPerson(id, name, tel, age, height))
        }
        cursor.close()
        db.close()
        return list
    }

    fun search(dbHelper: DbHelper, query: String): List<DbPerson> {
        val list = mutableListOf<DbPerson>()
        val db = dbHelper.writableDatabase

        val filters = mutableListOf<String>()
        val args = mutableListOf<String>()

        filters.add("name LIKE ?")
        args.add("%$query%")

        filters.add("tel LIKE ?")
        args.add("%$query%")

        query.toIntOrNull()?.let {
            filters.add("age = ?")
            args.add(it.toString())
        }

        query.toDoubleOrNull()?.let {
            filters.add("height = ?")
            args.add(it.toString())
        }

        val sql = "SELECT * FROM person WHERE " + filters.joinToString(" OR ")
        val cursor = db.rawQuery(sql, args.toTypedArray())

        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
            val name = cursor.getString(cursor.getColumnIndexOrThrow("name"))
            val tel = cursor.getString(cursor.getColumnIndexOrThrow("tel"))
            val age = cursor.getInt(cursor.getColumnIndexOrThrow("age"))
            val height = cursor.getDouble(cursor.getColumnIndexOrThrow("height"))
            list.add(DbPerson(id, name, tel, age, height))
        }
        cursor.close()
        db.close()
        return list
    }
}