package com.android_lesson

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android_lesson.databinding.ActivityMainBinding
import com.android_lesson.databinding.ActivityNewBinding

class NewActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityNewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        viewBinding = ActivityNewBinding.inflate(layoutInflater)
        setContentView(viewBinding.root) // setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        // data sending from one activity to another
        viewBinding.button11.setOnClickListener {
            val intent = Intent(this@NewActivity, NewActivity2::class.java)
            val name = viewBinding.editTextText.text.toString()
            val age = viewBinding.editTextNumber.text.toString().toInt()
//            intent.putExtra("name", name)
//            intent.putExtra("age", age)
            val person = Person(name, age)
            intent.putExtra("person", person)
            startActivity(intent)
        }


    }
}