package com.android_lesson

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.text.method.ScrollingMovementMethod
import com.android_lesson.databinding.ActivityZ4Binding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Z4Activity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityZ4Binding
    private lateinit var db: Z4Database
    private lateinit var personDao: Z4PersonDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        viewBinding = ActivityZ4Binding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        db = Z4Database.getInstance(this)
        personDao = db.getPersonDao()

        // TextView scroll özelliini aktif et
        viewBinding.textViewPersonList.movementMethod = ScrollingMovementMethod()

        loadPerson()
    }

    fun loadPerson() {
        val job = CoroutineScope(Dispatchers.Main).launch {
            val personList = personDao.getAll()
            val personListString = StringBuilder()
            for (person in personList) {
                personListString.append("Name: ${person.name}, Age: ${person.age}\n")
            }
            viewBinding.textViewPersonList.text = personListString.toString()
        }
    }
}