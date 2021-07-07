package com.example.todolist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_todo.view.*

class TodoAdapter(
        private val context: Context,
        private val listener: TodoCLickListener,
): RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    var todoList = ArrayList<dataTodo>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        var viewHolder =  TodoViewHolder(LayoutInflater.from(context).inflate(R.layout.item_todo, parent, false))

        viewHolder.checkBox.setOnClickListener {
            val position = viewHolder.adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClicked(todoList[position])
            }
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.textView.text = todoList[position].title
        var isChecked = false
        if(todoList[position].checkBox == 1) isChecked = true
        holder.checkBox.isChecked = isChecked
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    inner class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textView: TextView = itemView.tvTitleText
        var checkBox: CheckBox = itemView.cbDone
    }

    fun updateList(newList: List<dataTodo>) {
        todoList.clear()
        todoList.addAll(newList)
        notifyDataSetChanged()
    }
}
interface TodoCLickListener {
    fun onItemClicked(dataTodo: dataTodo)
}