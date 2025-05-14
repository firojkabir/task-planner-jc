package com.example.taskplannerjc.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val status: TaskStatus = TaskStatus.PENDING
)

enum class TaskStatus {
    PENDING, IN_PROGRESS, COMPLETE
}