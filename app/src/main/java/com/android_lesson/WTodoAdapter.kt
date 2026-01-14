package com.android_lesson

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android_lesson.databinding.WCardTodoBinding

class WTodoAdapter(
    private val todos: MutableList<Todo>,
    private val onCheckedChange: (Todo) -> Unit,
    private val onDeleteClick: (Todo) -> Unit,
    private val onEditClick: (Todo) -> Unit
) : RecyclerView.Adapter<WTodoAdapter.VH>() {

    // ViewHolder for each todo card
    class VH(val binding: WCardTodoBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = WCardTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val todo = todos[position]

        // Set todo text and checkbox
        holder.binding.tvTodo.text = todo.message

        // ✅ Remove previous listener before setting checked to avoid recycled view issues
        holder.binding.cbDone.setOnCheckedChangeListener(null)
        holder.binding.cbDone.isChecked = todo.completed
        // ✅ Add new listener
        holder.binding.cbDone.setOnCheckedChangeListener { _, checked ->
            todo.completed = checked
            onCheckedChange(todo)
        }

        // EDIT ICON → show edit dialog
        holder.binding.ivEdit.setOnClickListener {
            onEditClick(todo)
        }

        // DELETE ICON → remove todo
        holder.binding.ivDelete.setOnClickListener {
            onDeleteClick(todo)
        }
    }

    override fun getItemCount(): Int = todos.size
}
