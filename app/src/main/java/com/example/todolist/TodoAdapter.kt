package com.example.todolist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_todo.view.*

class TodoAdapter(var todos: List<Todo>): RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    inner class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textView: TextView = itemView.tvTitleText
        var checkBox: CheckBox = itemView.cbDone
        var deleteTitle: CheckBox = itemView.cbDelete
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        var view =  LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)
        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.textView.text = todos[position].title
        holder.checkBox.isChecked = todos[position].isChecked
        holder.deleteTitle.isChecked = todos[position].delete

//        holder.itemView.apply {
//            tvTitle.text = todos[position].title
//            cbDone.isChecked = todos[position].isChecked
//        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }
}