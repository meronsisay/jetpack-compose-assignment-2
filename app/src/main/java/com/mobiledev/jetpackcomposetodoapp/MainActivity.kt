package com.mobiledev.jetpackcomposetodoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.mobiledev.jetpackcomposetodoapp.data.model.TodoDatabase
import com.mobiledev.jetpackcomposetodoapp.data.remote.RetrofitInstance
import com.mobiledev.jetpackcomposetodoapp.repository.TodoRepository
import com.mobiledev.jetpackcomposetodoapp.navigation.NavGraph
import com.mobiledev.jetpackcomposetodoapp.ui.theme.JetpackComposeTodoAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val database = TodoDatabase.getDatabase(applicationContext)
        val dao = database.todoDao()


        val repository = TodoRepository(
            api = RetrofitInstance.api,
            dao = dao
        )


        setContent {
            JetpackComposeTodoAppTheme {
                NavGraph(repo = repository)
            }
        }
    }
}

