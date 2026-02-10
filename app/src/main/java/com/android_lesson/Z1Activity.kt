package com.android_lesson

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android_lesson.databinding.ActivityZ1Binding
import com.squareup.picasso.Picasso

class Z1Activity : AppCompatActivity() {

    private lateinit var binding: ActivityZ1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityZ1Binding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.buttonPicasso.setOnClickListener {
            val url = "https://picsum.photos/400"

            Picasso.get()
                .load(url)
                .resize(400, 400)
                .rotate(90f)
                .placeholder(R.drawable.baseline_edit_24)
                .error(R.drawable.baseline_delete_24)
                .into(binding.imageViewPicasso)
        }
    }
}