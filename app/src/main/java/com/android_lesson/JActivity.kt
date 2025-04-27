package com.android_lesson

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android_lesson.databinding.ActivityJBinding

class JActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityJBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        viewBinding = ActivityJBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Navigate to a new activity when the button is clicked
        viewBinding.btnPopupMenu.setOnClickListener {
            val popup = PopupMenu(this@JActivity, viewBinding.btnPopupMenu)

            popup.menuInflater.inflate(R.menu.popup_menu, popup.menu)

            popup.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.action_delete -> {
                        Toast.makeText(applicationContext, "Selected Delete", Toast.LENGTH_LONG)
                            .show()
                        true
                    }

                    R.id.action_delete -> {
                        Toast.makeText(applicationContext, "Selected Edit", Toast.LENGTH_LONG).show()
                        true
                    }

                    else -> false
                }
            }

            popup.show()
        }
    }
}