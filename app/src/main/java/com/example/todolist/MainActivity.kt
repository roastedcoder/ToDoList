package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var todoList = mutableListOf<Todo>(
            Todo("Follow me on instagram", isChecked = false, delete = false),
            Todo("Learn about recyclerView", isChecked = false, delete = false),
            Todo("Feed my cat", isChecked = false, delete = false),
            Todo("Prank my BOSS", isChecked = false, delete = false),
            Todo("Eat some curry", isChecked = false, delete = false),
            Todo("Aks my crush out", isChecked = false, delete = false),
            Todo("Take a shower", isChecked = false, delete = false),
        )

        var adapter = TodoAdapter(todoList)
        rvTodos.adapter = adapter
        rvTodos.layoutManager = LinearLayoutManager(this)

        btnAddTodo.setOnClickListener {
            val title = etTodo.text.toString()
            if(title.isNotEmpty()) {
                val todo = Todo(title, isChecked = false, delete = false)
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
            Toast.makeText(this, "Sorry, not working now", LENGTH_SHORT).show()
//            while(true) {
//                var letDelete = -1
//                for(i in todoList.indices) {
//                    if(todoList[i].delete) {
//                        letDelete = i
//                        break
//                    }
//                }
//                Log.d("Delete", "$letDelete")
//                if(letDelete == -1) {
//                    break
//                }
//                else {
//                    todoList.removeAt(letDelete)
//                    adapter.notifyItemRemoved(letDelete)
//                }
//            }
        }
    }
}