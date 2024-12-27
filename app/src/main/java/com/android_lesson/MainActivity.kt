package com.android_lesson

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.addCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android_lesson.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

// Importing kotlinx synthetic view binding library (deprecated as of Android's modern standards)
//import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var classicBinding: Button
    private lateinit var textView: TextView

    private lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // View Binding
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root) // setContentView(R.layout.activity_main)

        // Classic Binding
        classicBinding = findViewById(R.id.BTN_ClassicBinding)
        textView = findViewById(R.id.textView)
        classicBinding.setOnClickListener {
            textView.text = "classic binding example"
            Snackbar.make(it, "classic binding example", Snackbar.LENGTH_SHORT).show()
        }

        // Synthetic Binding
        // Note: Synthetic binding is no longer recommended and has been deprecated.
        // Use View Binding or findViewById instead for better type safety and performance.
        /*
                BTN_SyntheticBinding.setOnClickListener {
                    textView.text = "synthetic binding example"
                    Snackbar.make(it, "synthetic binding example", Snackbar.LENGTH_SHORT).show()
                }
        */

        // View Binding
        viewBinding.BTNViewBinding.setOnClickListener {
            textView.text = "view binding example"
            Snackbar.make(it, "view binding example", Snackbar.LENGTH_SHORT).show()
        }


        // This code enables the app to operate in full-screen mode by handling system bar insets
        // and applying appropriate padding to the content view.
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        // Navigate to a new activity when the button is clicked
        viewBinding.GoToB.setOnClickListener {
            val intent = Intent(this@MainActivity, NewActivity::class.java)
            startActivity(intent)
        }


        // Handle back button press
        onBackPressedDispatcher.addCallback(this) {
            val builder = AlertDialog.Builder(this@MainActivity)
            builder.setTitle("Exit")
            builder.setMessage("Do you want to close the application?")
            builder.setPositiveButton("Yes") { _, _ ->
                finish()
            }
            builder.setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }
            builder.create().show()
        }


        // to access xml resources in kotlin
        val title = R.string.app_name
        println(title)
        val image = R.drawable.info
        println(image)

        Log.d("Lifecycle onCreate", "onCreate called")
    }


    // Lifecycle methods

    override fun onStart() {
        super.onStart()
        Log.d("Lifecycle onStart", "onStart called")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Lifecycle onResume", "onResume called")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Lifecycle onPause", "onPause called")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Lifecycle onStop", "onStop called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Lifecycle onDestroy", "onDestroy called")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("Lifecycle onRestart", "onRestart called")
    }
}