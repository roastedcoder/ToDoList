package com.example.todolist

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), TodoCLickListener {

    lateinit var viewModel: TodoViewModel
    var todoCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_ToDoList)
        setContentView(R.layout.activity_main)

        rvTodos.layoutManager = LinearLayoutManager(this)
        val adapter = TodoAdapter(this, this)
        rvTodos.adapter = adapter

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(TodoViewModel::class.java)

        viewModel.allTodo.observe(this, Observer { list ->
            list?.let {
                adapter.updateList(it)
            }
        })

        val deleteDialog = AlertDialog.Builder(this)
            .setTitle("Do you want to remove?")
            .setMessage("Are you sure?")
            .setPositiveButton("REMOVE") { _, _ ->
                deleteSelected()
            }
            .setNegativeButton("CANCEL") { _, _ -> }
            .create()

        val dialogLayout = layoutInflater.inflate(R.layout.input_dialog, null)
        val input = dialogLayout.findViewById<EditText>(R.id.etInput)

        val inputDialog = AlertDialog.Builder(this)
            .setTitle("Add ToDo")
            .setIcon(R.drawable.ic_add_todo)
            .setView(dialogLayout)
            .setPositiveButton("ADD") { _, _ ->
                var title = input.text.toString()
                title = title.trim()

                if (title.isNotEmpty()) {
                    val todo = dataTodo(title, checkBox = 0)
                    todoCount += 1
                    viewModel.addTask(todo)
                    if(todoCount == 1) {
                        setTheme(R.style.Theme_ToDoList)
                    }
                    input.text.clear()
                }
                else {
                    input.text.clear()
                    Toast.makeText(this, "You don't have a todo", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("CANCEL") { _, _ ->
                input.text.clear()
            }
            .create()



        btnAddTodo.setOnClickListener {
            inputDialog.show()
        }

        btnDelete.setOnClickListener {
            Toast.makeText(this, "0 item selected", Toast.LENGTH_SHORT).show()
        }
    }

    private fun deleteSelected() {

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.actionSearch -> Toast.makeText(
                this,
                "Search Action Coming Soon...",
                Toast.LENGTH_SHORT
            ).show()
        }
        return true
    }

    fun insertTodo() {

    }

    override fun onItemClicked(dataTodo: dataTodo) {
        viewModel.deleteTask(dataTodo)
        todoCount -= 1
        if(todoCount == 0) {
            setTheme(R.style.splashScreenTheme)
        }
    }
}