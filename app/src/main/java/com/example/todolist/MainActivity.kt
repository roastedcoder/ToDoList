package com.example.todolist

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), TodoAdapter.OnItemCLickListener {
    private var todoList = mutableListOf<Todo>(
            Todo("Follow me on Instagram", false),
            Todo("Learn about recyclerView", false),
            Todo("Feed my cat", false),
            Todo("Prank my BOSS", false),
            Todo("Eat some curry", false),
            Todo("Ask my crush out", false),
            Todo("Take a shower", false),
            Todo("You can add an item now", false),
            Todo("Also you can delete from the list", false),
            Todo("But the app is still in progress", false),
            Todo("Hopefully we would complete this", false),
            Todo("that's why I have added so much of dummy data", false)
    )
    private var adapter = TodoAdapter(todoList, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val deleteDialog = AlertDialog.Builder(this)
            .setTitle("Do you want to delete?")
            .setMessage("Are you sure?")
            .setPositiveButton("DELETE") { _, _ ->
                deleteSelected()
            }
            .setNegativeButton("CANCEL") { _, _ ->

            }
            .create()

        rvTodos.adapter = adapter
        rvTodos.layoutManager = LinearLayoutManager(this)
        rvTodos.setHasFixedSize(true)

        btnAddTodo.setOnClickListener {
            val title = etTodo.text.toString()
            if(title.isNotEmpty()) {
                val todo = Todo(title, checkBox = false)
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
            var cnt = 0
            for(i in todoList.indices) {
                if(todoList[i].checkBox) {
                    cnt += 1
                }
            }
            if(cnt != 0) deleteDialog.show()
            else {
                Toast.makeText(this, "0 item selected", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun deleteSelected() {
        while(true) {
            var letDelete = -1
            for(i in todoList.indices) {
                if(todoList[i].checkBox) {
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

    override fun onItemClick(position: Int) {
        todoList[position].checkBox = !todoList[position].checkBox
    }
}