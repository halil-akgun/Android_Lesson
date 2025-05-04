package com.android_lesson

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android_lesson.databinding.ActivityNBinding

class NActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityNBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        viewBinding = ActivityNBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        viewBinding.btnInputEditText.setOnClickListener {
            val name = viewBinding.inputEditTextName.text.toString().trim()
            val tel = viewBinding.inputEditTextTel.text.toString().trim()

            if (name.isEmpty()) {
                Toast.makeText(applicationContext, "ENTER NAME", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (tel.isEmpty()) {
                Toast.makeText(applicationContext, "ENTER TEL", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            Toast.makeText(applicationContext, "$name - $tel", Toast.LENGTH_SHORT).show()
        }
    }
}