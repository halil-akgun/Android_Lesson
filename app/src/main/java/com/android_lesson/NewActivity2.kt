package com.android_lesson

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class NewActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_new2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        // data receiving from one activity to another
//        val name = intent.getStringExtra("name")
//        val age = intent.getIntExtra("age", 0)
//        val text = "$name ($age)"
        val person = intent.getSerializableExtra("person") as Person
        val text = "${person.name} (${person.age})"
        val textView = findViewById<TextView>(R.id.textView3)
        textView.text = text
    }
}