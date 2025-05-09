package com.example.taskplannerjc.model

enum class TaskState {
    Pending,
    InProgress,
    Complete
}

data class Task(
    val title: String,
    var state: TaskState = TaskState.Pending
){
    fun advance(): Task {
        return when (state) {
            TaskState.Pending -> copy(state = TaskState.InProgress)
            TaskState.InProgress -> copy(state = TaskState.Complete)
            TaskState.Complete -> this
        }
    }
}