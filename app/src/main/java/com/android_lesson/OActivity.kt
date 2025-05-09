package com.android_lesson

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android_lesson.databinding.ActivityOBinding

class OActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityOBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityOBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
//        enableEdgeToEdge()
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

        viewBinding.toolbar2.title = "Toolbar"
        setSupportActionBar(viewBinding.toolbar2)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_info -> {
                Toast.makeText(this, "Info", Toast.LENGTH_SHORT).show()
                return true
            }

            R.id.action_settings -> {
                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show()
                return true
            }

            R.id.action_add -> {
                Toast.makeText(this, "Add", Toast.LENGTH_SHORT).show()
                return true
            }

            R.id.action_exit -> {
                Toast.makeText(this, "Exit", Toast.LENGTH_SHORT).show()
                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }
}