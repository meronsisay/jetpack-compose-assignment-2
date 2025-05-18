package com.mobiledev.jetpackcomposetodoapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.mobiledev.jetpackcomposetodoapp.data.model.Todo
import com.mobiledev.jetpackcomposetodoapp.repository.TodoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: TodoRepository,
    todoId: Int
) : ViewModel() {

    private val _selectedTodo = MutableStateFlow<Todo?>(null)
    val selectedTodo: StateFlow<Todo?> = _selectedTodo

    init {
        viewModelScope.launch {
            _selectedTodo.value = repository.getTodoById(todoId)
        }
    }
}
