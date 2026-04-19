package com.android_lesson

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import javax.inject.Inject

class Z5Activity : AppCompatActivity() {

    // classic way
//    private lateinit var cargo: Z5Cargo
//    private lateinit var internet: Z5Internet

    // dagger way
    @Inject
    lateinit var cargo: Z5Cargo

    @Inject
    lateinit var internet: Z5Internet

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_z5)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // classic way
//        cargo = Z5Cargo()
//        internet = Z5Internet()

        // dagger way
        DaggerZ5AppComponent.builder().build().inject(this)
        /*
        1- add implementation 'com.google.dagger:dagger:2.59.2' and implementation 'com.google.dagger:dagger-compiler:2.59.2'
        2- create Z5AppModule
        3- create Z5AppComponent
        4- use @Inject in activity
         */

        cargo.send()
        internet.apply()
    }
}