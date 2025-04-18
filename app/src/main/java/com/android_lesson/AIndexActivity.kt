package com.android_lesson

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android_lesson.databinding.ActivityAindexBinding

class AIndexActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityAindexBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        viewBinding = ActivityAindexBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Navigate to a new activity when the button is clicked
        viewBinding.mainGoToA.setOnClickListener {
            val intent = Intent(this@AIndexActivity, MainActivity::class.java)
            startActivity(intent)
        }
        viewBinding.mainGoToB.setOnClickListener {
            val intent = Intent(this@AIndexActivity, BActivity::class.java)
            startActivity(intent)
        }
        viewBinding.mainGoToC.setOnClickListener {
            val intent = Intent(this@AIndexActivity, CActivity::class.java)
            startActivity(intent)
        }
        viewBinding.mainGoToD.setOnClickListener {
            val intent = Intent(this@AIndexActivity, DActivity::class.java)
            startActivity(intent)
        }
        viewBinding.mainGoToE.setOnClickListener {
            val intent = Intent(this@AIndexActivity, EActivity::class.java)
            startActivity(intent)
        }
        viewBinding.mainGoToF.setOnClickListener {
            val intent = Intent(this@AIndexActivity, FActivity::class.java)
            startActivity(intent)
        }
        viewBinding.mainGoToG.setOnClickListener {
            val intent = Intent(this@AIndexActivity, GActivity::class.java)
            startActivity(intent)
        }
        viewBinding.mainGoToH.setOnClickListener {
            val intent = Intent(this@AIndexActivity, HActivity::class.java)
            startActivity(intent)
        }
        viewBinding.mainGoToI.setOnClickListener {
            val intent = Intent(this@AIndexActivity, IActivity::class.java)
            startActivity(intent)
        }
    }
}