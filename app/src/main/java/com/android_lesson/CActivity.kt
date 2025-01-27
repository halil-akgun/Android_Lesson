package com.android_lesson

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android_lesson.databinding.ActivityCBinding

class CActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityCBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        viewBinding = ActivityCBinding.inflate(layoutInflater)
        setContentView(viewBinding.root) // setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activityC)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Navigate to a new activity when the button is clicked
        viewBinding.goToD.setOnClickListener {
            val intent = Intent(this@CActivity, DActivity::class.java)
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

        // enable javaScript
        viewBinding.webView.settings.javaScriptEnabled = true

        viewBinding.btnHerkul.setOnClickListener {
            viewBinding.webView.loadUrl("https://herkul.org")
        }

        viewBinding.btnGoogle.setOnClickListener {
            viewBinding.webView.loadUrl("https://www.google.com")
        }
    }
}