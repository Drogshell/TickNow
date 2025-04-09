package com.trevin.ticknow.data

import com.trevin.ticknow.data.database.TaskDao
import com.trevin.ticknow.data.model.Task
import kotlinx.coroutines.flow.Flow

/**
 * Abstracts the UI layer and the data layer.
 * Now if I want to change the way data is stored, I just need to swap the taskDao.
 */
class TaskRepository(private val taskDao: TaskDao) {

    suspend fun createTask(task: Task){
        taskDao.createTask(task)
    }

    fun getAllTasks(): Flow<List<Task>> {
        return taskDao.getAllTasks()
    }

    suspend fun updateTask(task: Task){
        taskDao.updateTask(task)
    }

    suspend fun deleteTask(task: Task){
        taskDao.deleteTask(task)
    }

}