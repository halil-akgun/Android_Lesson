package com.android_lesson

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.android_lesson.databinding.ActivityPBinding

class PActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityPBinding
    private lateinit var countries: ArrayList<P_Country>
    private lateinit var adapter: P_RVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        viewBinding = ActivityPBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Improves performance if changes in content do not change layout size
        viewBinding.recyclerView.setHasFixedSize(true)
        viewBinding.recyclerView.layoutManager = LinearLayoutManager(this)

        countries = ArrayList()
        countries.add(P_Country("Belgium", 1))
        countries.add(P_Country("France", 2))
        countries.add(P_Country("Germany", 3))
        countries.add(P_Country("Italy", 4))
        countries.add(P_Country("Netherlands", 5))
        countries.add(P_Country("Portugal", 6))
        countries.add(P_Country("Spain", 7))
        countries.add(P_Country("United Kingdom", 8))

        adapter = P_RVAdapter(this, countries)
        viewBinding.recyclerView.adapter = adapter
    }
}