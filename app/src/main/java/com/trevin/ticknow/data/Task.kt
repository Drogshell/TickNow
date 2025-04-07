package com.trevin.ticknow.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    @PrimaryKey(autoGenerate = true) val taskID: Int = 0,
    val title: String,
    val description: String? = null,
    val isStarred: Boolean = false,
    val isComplete: Boolean = false
)