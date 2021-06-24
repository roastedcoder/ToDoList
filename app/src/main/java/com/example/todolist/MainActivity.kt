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
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), TodoAdapter.OnItemCLickListener {
    // dummy data
    var todoList = mutableListOf<Todo>(
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
            Todo("that's why so much of dummy data", false)
    )
    private var adapter = TodoAdapter(todoList, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_ToDoList)
        setContentView(R.layout.activity_main)

        rvTodos.adapter = adapter
        rvTodos.layoutManager = LinearLayoutManager(this)
        rvTodos.setHasFixedSize(true)


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
                        val todo = Todo(title, checkBox = false)
                        if (!todoList.contains(todo)) {
                            todoList.add(todo)
                            adapter.notifyItemInserted(todoList.size - 1)
                        } else {
                            Toast.makeText(this, "Already there", Toast.LENGTH_SHORT).show()
                        }
                        input.text.clear()
                    } else {
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
            var cnt = 0
            for (i in todoList.indices) {
                if (todoList[i].checkBox) {
                    cnt += 1
                }
            }
            if (cnt != 0) deleteDialog.show()
            else {
                Toast.makeText(this, "0 item selected", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun deleteSelected() {
        while (true) {
            var letDelete = -1
            for (i in todoList.indices) {
                if (todoList[i].checkBox) {
                    letDelete = i
                    break
                }
            }
            Log.d("Delete", "$letDelete")
            if (letDelete == -1) {
                break
            } else {
                todoList.removeAt(letDelete)
                adapter.notifyItemRemoved(letDelete)
            }
        }
    }

    override fun onItemClick(position: Int) {
        todoList[position].checkBox = !todoList[position].checkBox
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.action_search, menu)


//        val searchItem = menu?.findItem(R.id.actionSearch)
//        val searchView = searchItem?.actionView as SearchView
//
//        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(p0: String?): Boolean {
//                return true
//            }
//
//            override fun onQueryTextChange(query: String?): Boolean {
//                adapter?.filter?.filter(query)
//                return true
//            }
//        })
//        searchItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
//            override fun onMenuItemActionCollapse(p0: MenuItem?): Boolean {
//                adapter?.filter?.filter("")
//                return true
//            }
//
//            override fun onMenuItemActionExpand(p0: MenuItem?): Boolean {
//                return true
//            }
//        })

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.actionSearch -> Toast.makeText(this, "Search Action Coming Soon...", Toast.LENGTH_SHORT).show()
        }
        return true
    }
}