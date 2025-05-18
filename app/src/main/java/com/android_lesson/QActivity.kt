package com.android_lesson

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.android_lesson.databinding.ActivityQBinding

class QActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityQBinding
    private lateinit var films: ArrayList<Q_Film>
    private lateinit var adapter: Q_Film_Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        viewBinding = ActivityQBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        viewBinding.filmRecyclerView.setHasFixedSize(true)
        viewBinding.filmRecyclerView.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        films = ArrayList()
        films.add(Q_Film(1, "Django", "django", 2.99))
        films.add(Q_Film(2, "Inception", "inception", 5.99))
        films.add(Q_Film(3, "Interstellar", "interstellar", 4.99))
        films.add(Q_Film(4, "The Hateful Eight", "thehatefuleight", 3.33))
        films.add(Q_Film(5, "The Pianist", "thepianist", 2.11))
        films.add(Q_Film(6, "Bir Zamanlar Anadoluda", "birzamanlaranadoluda", 2.00))

        adapter = Q_Film_Adapter(this, films)
        viewBinding.filmRecyclerView.adapter = adapter
    }
}