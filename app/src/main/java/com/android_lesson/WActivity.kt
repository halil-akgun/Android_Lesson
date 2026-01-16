package com.android_lesson

import android.content.Context
import android.os.Bundle
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.android_lesson.databinding.ActivityWBinding
import org.json.JSONObject

class WActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWBinding
    private val allTodos = mutableListOf<Todo>()
    private val todos = mutableListOf<Todo>()
    private lateinit var adapter: WTodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityWBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        fetchTodos()

        // Apply padding for system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // FloatingActionButton → add new todo
        binding.btnAddTodo.setOnClickListener {
            showAddTodoDialog()
        }

        // Search
        binding.volleySearch.addTextChangedListener(object : android.text.TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: android.text.Editable?) {
                searchTodo(s?.toString() ?: "")
            }
        })
    }

    private fun setupRecyclerView() {
        adapter = WTodoAdapter(
            todos,
            onCheckedChange = { todo -> updateTodo(todo) },
            onDeleteClick = { todo -> deleteTodo(todo) },
            onEditClick = { todo -> showEditTodoDialog(todo) }
        )

        binding.rvTodos.layoutManager = LinearLayoutManager(this)
        binding.rvTodos.adapter = adapter
    }

    // Fetch todos from API
    private fun fetchTodos() {
        val url = "https://6538c6baa543859d1bb1e611.mockapi.io/todos"

        val request = JsonArrayRequest(
            Request.Method.GET, url, null,
            { response ->
                allTodos.clear()
                todos.clear()

                for (i in 0 until response.length()) {
                    val obj = response.getJSONObject(i)
                    val todo = Todo(
                        obj.getString("id"),
                        obj.getString("message"),
                        obj.getBoolean("completed")
                    )
                    allTodos.add(todo)
                    todos.add(todo)
                }

                adapter.notifyDataSetChanged()
            },
            { error -> error.printStackTrace() }
        )

        VolleyClient.getQueue(this).add(request)
    }

    // Show dialog to add new todo
    private fun showAddTodoDialog() {
        val input = EditText(this)
        input.hint = "Enter new Todo"

        AlertDialog.Builder(this)
            .setTitle("New Todo")
            .setView(input)
            .setPositiveButton("Add") { _, _ ->
                val message = input.text.toString()
                if (message.isNotEmpty()) addTodo(message)
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    // Show dialog to edit existing todo
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

    // Add todo to API
    private fun addTodo(message: String) {
        val url = "https://6538c6baa543859d1bb1e611.mockapi.io/todos"

        val body = JSONObject().apply {
            put("message", message)
            put("completed", false) // ✅ default false
        }

        val request = JsonObjectRequest(
            Request.Method.POST, url, body,
            { response ->
                // Add locally without refetch
                todos.add(Todo(response.getString("id"), message, false))
                adapter.notifyItemInserted(todos.size - 1)
            },
            { error -> error.printStackTrace() }
        )

        VolleyClient.getQueue(this).add(request)
    }

    // Update todo in API
    private fun updateTodo(todo: Todo) {
        val url = "https://6538c6baa543859d1bb1e611.mockapi.io/todos/${todo.id}"

        val body = JSONObject().apply {
            put("message", todo.message)
            put("completed", todo.completed)
        }

        val request = JsonObjectRequest(
            Request.Method.PUT, url, body,
            {
                val index = todos.indexOfFirst { it.id == todo.id }
                if (index != -1) adapter.notifyItemChanged(index) // ✅ smooth update
            },
            { error -> error.printStackTrace() }
        )

        VolleyClient.getQueue(this).add(request)
    }

    // Delete todo from API
    private fun deleteTodo(todo: Todo) {
        val url = "https://6538c6baa543859d1bb1e611.mockapi.io/todos/${todo.id}"

        val request = JsonObjectRequest(
            Request.Method.DELETE, url, null,
            {
                val index = todos.indexOf(todo)
                if (index != -1) {
                    todos.removeAt(index)
                    adapter.notifyItemRemoved(index) // ✅ smooth delete
                }
            },
            { error -> error.printStackTrace() }
        )

        VolleyClient.getQueue(this).add(request)
    }

    private fun searchTodo(query: String) {
        val filteredTodos = if (query.isEmpty()) {
            allTodos // Show all todos if query is empty
        } else {
            allTodos.filter { it.message.contains(query, ignoreCase = true) }
        }

        // Update adapter with filtered results
        adapter.updateTodos(filteredTodos)
    }
}

// Todo data class
data class Todo(
    val id: String,
    var message: String,
    var completed: Boolean
)

// Volley singleton
object VolleyClient {
    private var queue: RequestQueue? = null

    fun getQueue(context: Context): RequestQueue {
        if (queue == null) {
            queue = Volley.newRequestQueue(context.applicationContext)
        }
        return queue!!
    }
}
