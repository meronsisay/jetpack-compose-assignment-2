package com.mobiledev.jetpackcomposetodoapp.data.remote

import com.mobiledev.jetpackcomposetodoapp.data.model.Todo
import retrofit2.http.GET

interface TodoApiService {
    @GET("todos")
    suspend fun getTodos(): List<Todo>
}