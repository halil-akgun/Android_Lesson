package com.android_lesson

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android_lesson.databinding.ActivityNew2Binding

class NewActivity2 : AppCompatActivity() {

    private lateinit var viewBinding: ActivityNew2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        viewBinding = ActivityNew2Binding.inflate(layoutInflater)
        setContentView(viewBinding.root) // setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Navigate to a new activity when the button is clicked
        viewBinding.goToD.setOnClickListener {
            val intent = Intent(this@NewActivity2, NewActivity3::class.java)
            startActivity(intent)
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