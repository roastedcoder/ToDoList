package com.example.todolist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoViewModel(application: Application): AndroidViewModel(application) {

    private val repository: TodoRepository
    val allTodo: LiveData <List <dataTodo>>
    init {
        val dao = TodoDB.getDatabase(application).getTodoDao()
        repository = TodoRepository(dao)
        allTodo = repository.allTodo
    }

    fun deleteTask(dataTodo: dataTodo) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(dataTodo)
    }

    fun addTask(dataTodo: dataTodo) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(dataTodo)
    }
}