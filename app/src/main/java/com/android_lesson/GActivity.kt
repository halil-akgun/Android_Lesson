package com.android_lesson

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android_lesson.databinding.ActivityGBinding
import androidx.core.net.toUri

class GActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityGBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        viewBinding = ActivityGBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        viewBinding.startVideo.setOnClickListener {
//            val address = Uri.parse("android.resource://" + packageName + "/" + R.raw.video)
            val address = ("android.resource://" + packageName + "/" + R.raw.video).toUri()
            viewBinding.videoView.setVideoURI(address)
            viewBinding.videoView.start()
        }

        viewBinding.stopVideo.setOnClickListener {
            viewBinding.videoView.stopPlayback()
        }

        // Navigate to a new activity when the button is clicked
        viewBinding.goToH.setOnClickListener {
            val intent = Intent(this@GActivity, HActivity::class.java)
            startActivity(intent)
        }
    }
}
