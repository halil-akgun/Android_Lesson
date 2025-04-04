package com.android_lesson

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.android_lesson.databinding.ActivityEBinding

class EActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityEBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        viewBinding = ActivityEBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activityE)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Find the NavHostFragment from the fragmentContainerView
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment

        // Link the BottomNavigationView with the NavController for navigation
        NavigationUI.setupWithNavController(
            viewBinding.bottomNav,
            navHostFragment.navController
        )

        // Navigate to a new activity when the button is clicked
        viewBinding.goToF.setOnClickListener {
            val intent = Intent(this@EActivity, FActivity::class.java)
            startActivity(intent)
        }
        viewBinding.goToG.setOnClickListener {
            val intent = Intent(this@EActivity, GActivity::class.java)
            startActivity(intent)
        }
    }
}