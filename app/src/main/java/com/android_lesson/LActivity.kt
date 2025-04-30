package com.android_lesson

import android.graphics.Color
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android_lesson.databinding.ActivityLBinding
import com.google.android.material.snackbar.Snackbar

class LActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityLBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        viewBinding = ActivityLBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        viewBinding.btnSnackbarNormal.setOnClickListener { x ->
            Snackbar.make(x, "Normal Snackbar", Snackbar.LENGTH_SHORT).show()
        }

        viewBinding.btnSnackbarUndo.setOnClickListener { x ->
//            Snackbar.make(it, "Undo Snackbar", Snackbar.LENGTH_LONG)
            // You can use 'it' instead of defining 'x' explicitly above.
            Snackbar.make(x, "Undo Snackbar", Snackbar.LENGTH_LONG)
//                .setAction("Undo") {
//                    Snackbar.make(x, "Undo clicked", Snackbar.LENGTH_SHORT).show()
                .setAction("Undo") { y ->
                    Snackbar.make(y, "Undo clicked", Snackbar.LENGTH_SHORT).show()
                }.show()
        }

        viewBinding.btnSnackbarCustom.setOnClickListener { view ->
            val sb = Snackbar.make(view, "Custom Snackbar", Snackbar.LENGTH_LONG)
            sb.setAction("Go") {

            }
            sb.setActionTextColor(Color.RED)
            sb.setTextColor(Color.BLUE)
            sb.setBackgroundTint(Color.GREEN)
            sb.show()
        }
    }
}