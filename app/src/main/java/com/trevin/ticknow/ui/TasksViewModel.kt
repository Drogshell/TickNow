package com.trevin.ticknow.ui

import androidx.lifecycle.ViewModel
import com.trevin.ticknow.TickNowApplication
import com.trevin.ticknow.data.Task
import kotlin.concurrent.thread

class TasksViewModel : ViewModel(){

    private val taskDao = TickNowApplication.taskDao

    // This is not ideal
    fun fetchTasks(callback: (List<Task>) -> Unit){
        thread {
           callback(taskDao.getAllTasks())
        }
    }

    fun updateTask(task: Task){
        thread {
            taskDao.updateTask(task)
        }
    }

    fun deleteTask(task: Task){
        thread {
            taskDao.deleteTask(task)
        }
    }
}