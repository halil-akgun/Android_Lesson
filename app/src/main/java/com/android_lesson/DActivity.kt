package com.android_lesson

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.addCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android_lesson.databinding.ActivityDBinding

class DActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityDBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        viewBinding = ActivityDBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Navigate to a new activity when the button is clicked
        viewBinding.goToE.setOnClickListener {
            val intent = Intent(this@DActivity, EActivity::class.java)
            // This method closes the current activity and removes it from the back stack,
            // ensuring the user cannot return to it using the back button.
            // when user presses back button on page E, it will go back to page C
            finish()

//            Toast.makeText(this, "Page D finished and removed from back stack", Toast.LENGTH_LONG).show()
//            Toast.makeText(this@DActivity, "Page D finished and removed from back stack", Toast.LENGTH_LONG).show()
            Toast.makeText(applicationContext, "Page D finished and removed from back stack", Toast.LENGTH_LONG).show()

            startActivity(intent)
        }

        // back button
        onBackPressedDispatcher.addCallback(this) {
            val intent = Intent(this@DActivity, BActivity::class.java)
            // This flag targets a specific activity in the activity back stack
            // and removes all activities above it. If the target activity already exists
            // in the stack, it will reuse the existing instance instead of creating a new one.
            // This is useful for navigating back to a previous activity and clearing intermediate ones.
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }
    }
}