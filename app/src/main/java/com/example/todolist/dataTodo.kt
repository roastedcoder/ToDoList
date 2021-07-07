package com.example.todolist

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_table")
class dataTodo ( @ColumnInfo(name = "title") var title: String, @ColumnInfo(name = "checkBox") var checkBox: Int ) {
    @PrimaryKey(autoGenerate = true) var id = 0
}