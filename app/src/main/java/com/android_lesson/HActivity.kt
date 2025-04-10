package com.android_lesson

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android_lesson.databinding.ActivityHBinding

class HActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityHBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        viewBinding = ActivityHBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Navigate to a new activity when the button is clicked
        viewBinding.goToI.setOnClickListener {
            val intent = Intent(this@HActivity, IActivity::class.java)
            startActivity(intent)
        }
    }
}