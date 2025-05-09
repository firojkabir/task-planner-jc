package com.example.taskplannerjc.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskplannerjc.model.Task
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TaskViewModel : ViewModel() {
    private val _taskList = mutableStateListOf<Task>()
    val taskList: List<Task> get() = _taskList

    fun loadTasks() {
        viewModelScope.launch {
            delay(500)
            _taskList.addAll(
                listOf(
                    Task("Read professor feedback"),
                    Task("Write Jetpack Compose chapter"),
                    Task("Integrate coroutines")
                )
            )
        }
    }

    fun addTask(title: String) {
        viewModelScope.launch {
            delay(300)
            _taskList.add(Task(title))
        }
    }

    fun advanceTaskState(task: Task) {
        viewModelScope.launch {
            delay(200)
            val index = _taskList.indexOf(task)
            if (index != -1) {
                _taskList[index] = task.advance()
            }
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            delay(250)
            _taskList.remove(task)
        }
    }
}