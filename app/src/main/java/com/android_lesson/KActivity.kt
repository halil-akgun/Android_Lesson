package com.android_lesson

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android_lesson.databinding.ActivityKBinding

class KActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityKBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        viewBinding = ActivityKBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        viewBinding.btnNormalAlertview.setOnClickListener {
            val dialog = AlertDialog.Builder(this@KActivity)
            dialog.setTitle("Title")
            dialog.setMessage("Message")
            dialog.setIcon(R.drawable.baseline_cruelty_free_24)

            dialog.setPositiveButton("OK") { _, _ ->
                Toast.makeText(applicationContext, "Clicked OK", Toast.LENGTH_LONG).show()
            }
            dialog.setNegativeButton("Cancel") { _, _ ->
                Toast.makeText(applicationContext, "Clicked Cancel", Toast.LENGTH_LONG).show()
            }
            dialog.show()
        }

        viewBinding.btnCustomAlertview.setOnClickListener {

        }
    }
}