package com.example.taskplannerjc.repository

import com.example.taskplannerjc.model.TaskDao
import com.example.taskplannerjc.model.Task
import kotlinx.coroutines.flow.Flow

class TaskRepository(private val taskDao: TaskDao) {
    val allTasks: Flow<List<Task>> = taskDao.getAllTasks()

    suspend fun insert(task: Task) = taskDao.insert(task)
    suspend fun delete(task: Task) = taskDao.delete(task)
    suspend fun update(task: Task) = taskDao.update(task)
}