package com.mobiledev.jetpackcomposetodoapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobiledev.jetpackcomposetodoapp.repository.TodoRepository
import kotlinx.coroutines.launch

class ListViewModel(private val repository: TodoRepository) : ViewModel() {

    val todos = repository.cachedTodos
    val isLoading = repository.isLoading
    val errorMessage = repository.errorMessage

    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            repository.fetchTodos(forceRefresh = true)
        }
    }
}


