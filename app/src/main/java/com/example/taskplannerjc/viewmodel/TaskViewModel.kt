package com.example.taskplannerjc.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.taskplannerjc.model.Task
import com.example.taskplannerjc.model.TaskState

class TaskViewModel : ViewModel() {
    val tasks = mutableStateListOf<Task>()

    fun addTask(title: String) {
        tasks.add(Task(title = title))
    }

    fun advanceTaskState(task: Task) {
        val index = tasks.indexOf(task)
        if (index != -1) {
            tasks[index] = task.copy(state = nextState(task.state))
        }
    }

    private fun nextState(current: TaskState): TaskState = when (current) {
        TaskState.Pending -> TaskState.InProgress
        TaskState.InProgress -> TaskState.Complete
        TaskState.Complete -> TaskState.Complete
    }
}
