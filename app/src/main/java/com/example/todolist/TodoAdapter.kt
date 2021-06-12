package com.example.todolist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_todo.view.*

class TodoAdapter(
        private var todos: List<Todo>,
        private val listener: OnItemCLickListener
): RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        var view =  LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)
        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.textView.text = todos[position].title
        holder.checkBox.isChecked = todos[position].checkBox
    }

    override fun getItemCount(): Int {
        return todos.size
    }

    inner class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var textView: TextView = itemView.tvTitleText
        var checkBox: CheckBox = itemView.cbDone

        init {
            itemView.cbDone.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if(position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }
    interface OnItemCLickListener {
        fun onItemClick(position: Int)
    }
}