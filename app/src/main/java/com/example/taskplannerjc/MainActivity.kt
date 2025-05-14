package com.example.taskplannerjc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.Factory
import com.example.taskplannerjc.model.TaskDatabase
import com.example.taskplannerjc.repository.TaskRepository
import com.example.taskplannerjc.view.TaskScreen
import com.example.taskplannerjc.viewmodel.TaskViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val db = TaskDatabase.getDatabase(applicationContext)
        val repository = TaskRepository(db.taskDao())

        setContent {
            val viewModel: TaskViewModel = viewModel(factory = object : Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return TaskViewModel(repository) as T
                }
            })
            TaskScreen(viewModel = viewModel)
        }
    }
}
