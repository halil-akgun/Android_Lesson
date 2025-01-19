package com.android_lesson

import android.os.Bundle
import android.widget.TextView
import androidx.activity.addCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.android_lesson.databinding.ActivityFBinding

class FActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityFBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        viewBinding = ActivityFBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activityF)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Find the NavHostFragment from the fragmentContainerView
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView2) as NavHostFragment

        // Link the BottomNavigationView with the NavController for navigation
        NavigationUI.setupWithNavController(
            viewBinding.sideNavActivityF,
            navHostFragment.navController
        )

        viewBinding.toolbar.title = "Title"

        val toggle = ActionBarDrawerToggle(
            this,
            viewBinding.activityF,
            viewBinding.toolbar,
            0,
            0
        )
        viewBinding.activityF.addDrawerListener(toggle)
        toggle.syncState()

        val title2 = viewBinding.sideNavActivityF.inflateHeaderView(R.layout.nav_drawer_title)
        val title3: TextView = title2.findViewById(R.id.nav_drawer_text)
        title3.text = "Title..."

        // back button
        onBackPressedDispatcher.addCallback(this) {
            if (viewBinding.activityF.isDrawerOpen(GravityCompat.START)) {
                viewBinding.activityF.closeDrawer(GravityCompat.START)
            } else {
                // deprecated in API 33+
//                super.onBackPressed()
                // Handles back button press with the modern onBackPressedDispatcher API.
                // This method can be customized.
                onBackPressedDispatcher.onBackPressed()
            }
        }
    }
}