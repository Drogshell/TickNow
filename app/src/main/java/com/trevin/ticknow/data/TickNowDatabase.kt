package com.trevin.ticknow.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Task::class], version = 1)
abstract class TickNowDatabase : RoomDatabase() {

    abstract fun getTaskDao(): TaskDao

    companion object {
        fun createDatabase(context: Context): TickNowDatabase {
            return Room.databaseBuilder(context, TickNowDatabase::class.java, "tick-now-database")
                .build()
        }
    }

}