package com.example.todolist

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(dataTodo: dataTodo)

    @Delete
    suspend fun delete(dataTodo: dataTodo)

    @Query("SELECT * FROM todo_table ORDER BY id ASC")
    fun getAllTodo(): LiveData <List <dataTodo>>
}