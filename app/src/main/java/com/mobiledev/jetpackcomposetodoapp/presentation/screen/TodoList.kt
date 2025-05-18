package com.mobiledev.jetpackcomposetodoapp.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mobiledev.jetpackcomposetodoapp.presentation.viewmodel.ListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(
    viewModel: ListViewModel,
    onTodoClick: (Int) -> Unit
) {
    val todos = viewModel.todos.collectAsState().value
    val isLoading = viewModel.isLoading.collectAsState().value
    val error = viewModel.errorMessage.collectAsState().value

    Column(modifier = Modifier.fillMaxSize()) {

        TopAppBar(
            title = {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("TODO List")
                }
            },
            colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = Color(0xFF7C4DFF), // Your purple color
                titleContentColor = Color.White
            )

        )


        if (isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = Color(0xFF7C4DFF))
            }
        } else {
            if (!error.isNullOrEmpty()) {
                Text(
                    text = error,
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    color = Color(0xFFD55C5C),
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium
                )
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFF3E5F5)), // very light purple background
                contentPadding = PaddingValues(vertical = 8.dp)
            ) {
                items(todos) { todo ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp, vertical = 10.dp)
                            .clickable { onTodoClick(todo.id) },
                        shape = RoundedCornerShape(12.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                    ) {
                        Column(modifier = Modifier.padding(22.dp)) {
                            Text(
                                text = todo.title,
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.titleMedium,
                                color = Color(0xFF4A148C)
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = "Status: ${if (todo.completed) "Complete" else "Incomplete"}",
                                style = MaterialTheme.typography.bodySmall,
                                color = if (todo.completed) Color(0xFF7B1FA2) else Color.Gray
                            )
                        }
                    }
                }
            }
        }
    }
}


