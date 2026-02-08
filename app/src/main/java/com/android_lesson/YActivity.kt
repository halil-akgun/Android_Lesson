package com.android_lesson

import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.android_lesson.databinding.ActivityYBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.IgnoreExtraProperties
import com.google.firebase.database.ValueEventListener

class YActivity : AppCompatActivity() {

    private lateinit var binding: ActivityYBinding
    private lateinit var adapter: WTodoAdapter

    private val allTodos = mutableListOf<Todo>() // master list
    private val todos = mutableListOf<Todo>()    // shown list

    private val database = FirebaseDatabase
        .getInstance("https://deneme-school-default-rtdb.europe-west1.firebasedatabase.app")
        .getReference("todos")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityYBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupRecyclerView()
        observeTodos()

        binding.btnAddTodo2.setOnClickListener {
            showAddTodoDialog()
        }

        // Search
        binding.firebaseSearch.addTextChangedListener(object : android.text.TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: android.text.Editable?) {
                searchTodo(s?.toString() ?: "")
            }
        })
    }

    private fun showAddTodoDialog() {
        val input = EditText(this)
        input.hint = "New todo"

        AlertDialog.Builder(this)
            .setTitle("Add Todo")
            .setView(input)
            .setPositiveButton("Add") { _, _ ->
                val message = input.text.toString().trim()
                if (message.isNotEmpty()) {
                    addTodo(message)
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun addTodo(message: String) {
        val newTodo = TodoFirebase(message, false)

        database.push().setValue(newTodo)
            .addOnSuccessListener {
                Log.d("FIREBASE", "Todo added")
            }
            .addOnFailureListener {
                Log.e("FIREBASE", "Error", it)
            }
    }

    private fun searchTodo(query: String) {
        todos.clear()

        if (query.isEmpty()) {
            todos.addAll(allTodos)
        } else {
            todos.addAll(
                allTodos.filter {
                    it.message.contains(query, ignoreCase = true)
                }
            )
        }

        adapter.notifyDataSetChanged()
    }

    private fun setupRecyclerView() {
        adapter = WTodoAdapter(
            todos,
            onCheckedChange = { updateTodo(it) },
            onDeleteClick = { deleteTodo(it) },
            onEditClick = { showEditTodoDialog(it) }
        )

        binding.rvTodos2.layoutManager = LinearLayoutManager(this)
        binding.rvTodos2.adapter = adapter
    }

    private fun observeTodos() {
        database.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                allTodos.clear()
                todos.clear()

                for (child in snapshot.children) {
                    val firebaseTodo =
                        child.getValue(TodoFirebase::class.java)

                    if (firebaseTodo != null) {
                        val todo = Todo(
                            id = child.key ?: "",
                            message = firebaseTodo.message,
                            completed = firebaseTodo.completed
                        )

                        allTodos.add(todo)
                        todos.add(todo)
                    }
                }

                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                error.toException().printStackTrace()
            }
        })
    }

    private fun updateTodo(todo: Todo) {
        database.child(todo.id).setValue(
            TodoFirebase(todo.message, todo.completed)
        )
    }

    private fun deleteTodo(todo: Todo) {
        database.child(todo.id).removeValue()
    }

    private fun showEditTodoDialog(todo: Todo) {
        val input = EditText(this)
        input.setText(todo.message)

        AlertDialog.Builder(this)
            .setTitle("Edit Todo")
            .setView(input)
            .setPositiveButton("Save") { _, _ ->
                val newMessage = input.text.toString()
                if (newMessage.isNotEmpty()) {
                    todo.message = newMessage
                    updateTodo(todo)
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
}

@IgnoreExtraProperties
data class TodoFirebase(
    var message: String = "",
    var completed: Boolean = false
)