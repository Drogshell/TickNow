package com.trevin.ticknow.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trevin.ticknow.TickNowApplication
import com.trevin.ticknow.data.TaskRepository
import com.trevin.ticknow.data.model.Task
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val repository: TaskRepository = TickNowApplication.taskRepository

    fun createTask(title: String, description: String){
        val task = Task(
            title = title,
            description = description
        )
        viewModelScope.launch{
            repository.createTask(task)
        }
    }
}