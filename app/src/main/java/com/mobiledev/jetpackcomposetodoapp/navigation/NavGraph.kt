package com.mobiledev.jetpackcomposetodoapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mobiledev.jetpackcomposetodoapp.presentation.viewmodel.DetailViewModel
import com.mobiledev.jetpackcomposetodoapp.repository.TodoRepository
import com.mobiledev.jetpackcomposetodoapp.presentation.screen.DetailScreen
import com.mobiledev.jetpackcomposetodoapp.presentation.screen.ListScreen
import com.mobiledev.jetpackcomposetodoapp.presentation.viewmodel.ListViewModel

@Composable
fun NavGraph(startDestination: String = "list", repo: TodoRepository) {
    val navController = rememberNavController()
    val listViewModel = remember { ListViewModel(repo) }

    NavHost(navController = navController, startDestination = startDestination) {
        composable("list") {
            ListScreen(
                viewModel = listViewModel,
                onTodoClick = { navController.navigate("detail/$it") }
            )
        }
        composable("detail/{todoId}") { backStackEntry ->
            val todoId = backStackEntry.arguments?.getString("todoId")?.toIntOrNull() ?: return@composable
            val detailViewModel = remember(todoId) { DetailViewModel(repo, todoId) }
            val todoState = detailViewModel.selectedTodo.collectAsState()

            DetailScreen(todo = todoState.value) {
                navController.popBackStack()
            }
        }
    }
}

