package com.example.todolist

import androidx.lifecycle.LiveData

class TodoRepository(private val todoDao: TodoDao) {

    val allTodo: LiveData <List <dataTodo>> = todoDao.getAllTodo()

    suspend fun insert(dataTodo: dataTodo) {
        todoDao.insert(dataTodo)
    }

    suspend fun delete(dataTodo: dataTodo) {
        todoDao.delete(dataTodo)
    }
}