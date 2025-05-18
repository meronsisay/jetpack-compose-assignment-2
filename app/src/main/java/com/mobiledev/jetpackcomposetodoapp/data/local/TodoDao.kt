package com.mobiledev.jetpackcomposetodoapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mobiledev.jetpackcomposetodoapp.data.model.Todo

@Dao
interface TodoDao {
    @Query("SELECT * FROM todos")
    suspend fun getAllTodos(): List<Todo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodos(todos: List<Todo>)
}
