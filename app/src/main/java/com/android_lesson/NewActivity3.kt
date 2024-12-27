package com.android_lesson

import android.content.Intent
import android.os.Bundle
import androidx.activity.addCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android_lesson.databinding.ActivityNew3Binding

class NewActivity3 : AppCompatActivity() {

    private lateinit var viewBinding: ActivityNew3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        viewBinding = ActivityNew3Binding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // back button
        onBackPressedDispatcher.addCallback(this) {
            val intent = Intent(this@NewActivity3, NewActivity::class.java)
            // This flag targets a specific activity in the activity back stack
            // and removes all activities above it. If the target activity already exists
            // in the stack, it will reuse the existing instance instead of creating a new one.
            // This is useful for navigating back to a previous activity and clearing intermediate ones.
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }
    }
}