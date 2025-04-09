package com.trevin.ticknow.ui

import androidx.lifecycle.ViewModel
import com.trevin.ticknow.TickNowApplication
import com.trevin.ticknow.data.Task
import kotlin.concurrent.thread

class MainViewModel : ViewModel() {

    private val taskDao = TickNowApplication.taskDao

    fun createTask(title: String, description: String){
        val task = Task(
            title = title,
            description = description
        )
        thread {
            taskDao.createTask(task)
        }
    }
}