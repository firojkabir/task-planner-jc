package com.example.taskplannerjc.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskplannerjc.model.Task
import com.example.taskplannerjc.model.TaskStatus
import com.example.taskplannerjc.repository.TaskRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class TaskViewModel(private val repository: TaskRepository) : ViewModel() {
    val tasks: StateFlow<List<Task>> = repository.allTasks.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )

    fun addTask(title: String) {
        viewModelScope.launch {
            repository.insert(Task(title = title))
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            repository.delete(task)
        }
    }

    fun advanceStatus(task: Task) {
        val newStatus = when (task.status) {
            TaskStatus.PENDING -> TaskStatus.IN_PROGRESS
            TaskStatus.IN_PROGRESS -> TaskStatus.COMPLETE
            TaskStatus.COMPLETE -> return
        }
        viewModelScope.launch {
            repository.update(task.copy(status = newStatus))
        }
    }
}