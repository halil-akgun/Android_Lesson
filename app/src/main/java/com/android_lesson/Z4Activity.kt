package com.android_lesson

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.text.method.ScrollingMovementMethod
import android.widget.Toast
import com.android_lesson.databinding.ActivityZ4Binding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Z4Activity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityZ4Binding
    private lateinit var db: Z4Database
    private lateinit var personDao: Z4PersonDao
    private lateinit var personList: List<Z4Person>

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

        viewBinding.buttonRoomAdd.setOnClickListener {
            addPerson()
        }
        viewBinding.buttonRoomUpdate.setOnClickListener {
            updatePerson()
        }
        viewBinding.buttonRoomDelete.setOnClickListener {
            deletePerson()
        }
    }

    fun loadPerson() {
        val job = CoroutineScope(Dispatchers.Main).launch {
            personList = personDao.getAll()
            val personListString = StringBuilder()
            for (person in personList) {
                personListString.append("${person.id} - ${person.name} - ${person.age}\n")
            }
            viewBinding.textViewPersonList.text = personListString.toString()
        }
    }

    private fun addPerson() {
        val name = viewBinding.editTextRoomName.text.toString()
        val age = viewBinding.editTextRoomAge.text.toString().toInt()

        val person = Z4Person(0, name, age)

        CoroutineScope(Dispatchers.IO).launch {
            personDao.insert(person)
            // After insertion, reload the person list on the main thread
            CoroutineScope(Dispatchers.Main).launch {
                loadPerson()
            }
        }
    }

    private fun updatePerson() {
        val id = viewBinding.editTextRoomId.text.toString().toIntOrNull() ?: 0
        val name = viewBinding.editTextRoomName.text.toString()
        val age = viewBinding.editTextRoomAge.text.toString().toIntOrNull() ?: 0

        if (id == 0 || !idCheck(id)) {
            Toast.makeText(this, "Id not found", Toast.LENGTH_SHORT).show()
            return
        }

        val person = Z4Person(id, name, age)

        CoroutineScope(Dispatchers.IO).launch {
            personDao.update(person)
            // After update, reload the person list on the main thread
            CoroutineScope(Dispatchers.Main).launch {
                loadPerson()
            }
        }
    }

    private fun deletePerson() {
        val id = viewBinding.editTextRoomId.text.toString().toIntOrNull() ?: 0
        val name = viewBinding.editTextRoomName.text.toString()
        val age = viewBinding.editTextRoomAge.text.toString().toIntOrNull() ?: 0

        if (id == 0 || !idCheck(id)) {
            Toast.makeText(this, "Id not found", Toast.LENGTH_SHORT).show()
            return
        }

        val person = Z4Person(id, name, age)

        CoroutineScope(Dispatchers.IO).launch {
            personDao.delete(person)
            // After deletion, reload the person list on the main thread
            CoroutineScope(Dispatchers.Main).launch {
                loadPerson()
            }
        }
    }

    private fun idCheck(id: Int): Boolean {
        return personList.any { it.id == id }
    }
}