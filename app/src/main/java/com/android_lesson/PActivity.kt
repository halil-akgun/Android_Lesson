package com.android_lesson

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
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

        // Default vertical list layout
//        viewBinding.recyclerView.layoutManager = LinearLayoutManager(this)
        // Vertical list layout (direction explicitly set)
//        viewBinding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        // Horizontal list layout
//        viewBinding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        // Vertical staggered grid layout with 2 columns
        viewBinding.recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        // Horizontal staggered grid layout with 2 rows
//        viewBinding.recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL)


        countries = ArrayList()
        countries.add(P_Country("Belgium", 1))
        countries.add(P_Country("France", 2))
        countries.add(P_Country("Germany", 3))
        countries.add(P_Country("Italy", 4))
        countries.add(P_Country("Netherlands", 5))
        countries.add(P_Country("Portugal", 6))
        countries.add(P_Country("Spain", 7))
        countries.add(P_Country("United Kingdom", 8))
        countries.add(P_Country("United States", 9))
        countries.add(P_Country("Australia", 10))
        countries.add(P_Country("Brazil", 11))
        countries.add(P_Country("Canada", 12))
        countries.add(P_Country("India", 13))
        countries.add(P_Country("Mexico", 14))
        countries.add(P_Country("Russia", 15))
        countries.add(P_Country("Venezuela", 16))
        countries.add(P_Country("Ukraine", 17))
        countries.add(P_Country("Vietnam", 18))
        countries.add(P_Country("Argentina", 19))
        countries.add(P_Country("Chile", 20))
        countries.add(P_Country("Colombia", 21))
        countries.add(P_Country("Ecuador", 22))
        countries.add(P_Country("Peru", 23))
        countries.add(P_Country("Uruguay", 24))
        countries.add(P_Country("Bolivia", 25))
        countries.add(P_Country("Paraguay", 26))
        countries.add(P_Country("Chile", 27))
        countries.add(P_Country("Dominican Republic", 28))
        countries.add(P_Country("Ecuador", 29))
        countries.add(P_Country("Honduras", 30))
        countries.add(P_Country("Nicaragua", 31))
        countries.add(P_Country("Panama", 32))
        countries.add(P_Country("Costa Rica", 33))

        adapter = P_RVAdapter(this, countries)
        viewBinding.recyclerView.adapter = adapter
    }
}