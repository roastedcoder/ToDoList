package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), TodoAdapter.OnItemCLickListener {
    private var todoList = mutableListOf<Todo>(
            Todo("Follow me on Instagram", checkBox = false, deleteBox = false),
            Todo("Learn about recyclerView", checkBox = false, deleteBox = false),
            Todo("Feed my cat", checkBox = false, deleteBox = false),
            Todo("Prank my BOSS", checkBox = false, deleteBox = false),
            Todo("Eat some curry", checkBox = false, deleteBox = false),
            Todo("Aks my crush out", checkBox = false, deleteBox = false),
            Todo("Take a shower", checkBox = false, deleteBox = false),
    )
    private var adapter = TodoAdapter(todoList, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvTodos.adapter = adapter
        rvTodos.layoutManager = LinearLayoutManager(this)
        rvTodos.setHasFixedSize(true)

        btnAddTodo.setOnClickListener {
            val title = etTodo.text.toString()
            if(title.isNotEmpty()) {
                val todo = Todo(title, checkBox = false, deleteBox = true)
                if(!todoList.contains(todo)) {
                    todoList.add(todo)
                    adapter.notifyItemInserted(todoList.size - 1)
                }
                else {
                    Toast.makeText(this, "Already there", Toast.LENGTH_SHORT).show()
                }
                etTodo.text.clear()
            }
            else {
                Toast.makeText(this, "You don't have a todo", Toast.LENGTH_SHORT).show()
            }
        }


        btnDelete.setOnClickListener {
            while(true) {
                var letDelete = -1
                for(i in todoList.indices) {
                    if(todoList[i].deleteBox) {
                        letDelete = i
                        break
                    }
                }
                Log.d("Delete", "$letDelete")
                if(letDelete == -1) {
                    break
                }
                else {
                    todoList.removeAt(letDelete)
                    adapter.notifyItemRemoved(letDelete)
                }
            }
        }
    }

    override fun onItemClick(position: Int) {
        todoList[position].deleteBox = !todoList[position].deleteBox
    }
}