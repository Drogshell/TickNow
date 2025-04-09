package com.trevin.ticknow

import android.app.Application
import com.trevin.ticknow.data.TaskDao
import com.trevin.ticknow.data.TickNowDatabase

class TickNowApplication : Application() {

    // One time application global set up
    override fun onCreate() {
        super.onCreate()
        database = TickNowDatabase.getDatabase(this)
        taskDao = database.getTaskDao()
    }

    companion object {
        lateinit var database: TickNowDatabase
        lateinit var taskDao: TaskDao
    }

}