package com.example.taskplannerjc.model

enum class TaskState {
    Pending,
    InProgress,
    Complete
}

data class Task(
    val id: String = java.util.UUID.randomUUID().toString(),
    val title: String,
    var state: TaskState = TaskState.Pending
)