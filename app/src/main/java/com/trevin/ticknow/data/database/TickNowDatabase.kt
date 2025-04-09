package com.trevin.ticknow.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.trevin.ticknow.data.model.Task

@Database(entities = [Task::class], version = 2)
abstract class TickNowDatabase : RoomDatabase() {

    abstract fun getTaskDao(): TaskDao

    companion object {

        // This code is not modern but it works and I'll update it to something better later.
        @Volatile
        private var DATABASE_INSTANCE: TickNowDatabase? = null

        fun getDatabase(context: Context): TickNowDatabase {

            // This code follows the singleton pattern and ensures that only once instance of the
            // database is ever created and used. It's also thread safe.
            return DATABASE_INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    TickNowDatabase::class.java,
                    "tick-now-database"
                ).fallbackToDestructiveMigration().build() // You would never use this code in an actual app
                DATABASE_INSTANCE = instance
                instance // return implicit
            }
        }
    }
}