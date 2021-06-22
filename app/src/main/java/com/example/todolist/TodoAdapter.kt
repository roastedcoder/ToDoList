package com.example.todolist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_todo.view.*

class TodoAdapter(
        private var todos: List<Todo>,
        private val listener: OnItemCLickListener,
): RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    var todoList = ArrayList<Todo>()

    init {
        todoList = todos as ArrayList<Todo>
    }

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

//    override fun getFilter(): Filter {
//        return object : Filter() {
//            override fun performFiltering(constraint: CharSequence?): FilterResults {
//                val charSearch = constraint.toString()
//                todoList = if (charSearch.isEmpty()) {
//                    todos as ArrayList<Todo>
//                } else {
//                    val resultList = ArrayList<Todo>()
//                    for (row in todos) {
//                        if (row.title.toLowerCase().contains(constraint.toString().toLowerCase())) {
//                            resultList.add(row)
//                        }
//                    }
//                    resultList
//                }
//                val filterResults = FilterResults()
//                filterResults.values = todoList
//                return filterResults
//            }
//
//            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
//                todoList = results?.values as ArrayList<Todo>
//                notifyDataSetChanged()
//            }
//        }
//    }
}