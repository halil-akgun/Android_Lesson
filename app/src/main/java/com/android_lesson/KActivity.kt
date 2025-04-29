package com.android_lesson

import android.os.Bundle
import android.widget.EditText
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
            val customAlert = layoutInflater.inflate(R.layout.activity_k_customalertlayout, null)
            // We pass null as the root because we are not attaching this layout to any parent ViewGroup at this point.
            val editTextAlert: EditText = customAlert.findViewById(R.id.editTextCustomAlert)

            val dialog = AlertDialog.Builder(this@KActivity)
            dialog.setTitle("Title")
            dialog.setMessage("Message")
            dialog.setIcon(R.drawable.baseline_cruelty_free_24)
            dialog.setView(customAlert)

            dialog.setPositiveButton("Save") { _, _ ->
                val data = editTextAlert.text.toString()
                Toast.makeText(applicationContext, "Saved: $data", Toast.LENGTH_LONG).show()
//                Toast.makeText(applicationContext, "Saved: ${editTextAlert.text}", Toast.LENGTH_LONG).show()
                // Use ${} to access methods or expressions inside a string template
            }
            dialog.setNegativeButton("Cancel") { _, _ ->
                Toast.makeText(applicationContext, "Clicked Cancel", Toast.LENGTH_LONG).show()
            }
            dialog.show()
        }
    }
}