package com.example.taskplannerjc.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.taskplannerjc.model.Task
import com.example.taskplannerjc.model.TaskStatus
import com.example.taskplannerjc.viewmodel.TaskViewModel

@Composable
fun TaskScreen(viewModel: TaskViewModel) {
    val taskList by viewModel.tasks.collectAsState()
    var title by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("New Task") },
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = {
                if (title.isNotBlank()) {
                    viewModel.addTask(title)
                    title = ""
                }
            },
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text("Add Task")
        }

        LazyColumn(modifier = Modifier.padding(top = 16.dp)) {
            items(taskList) { task ->
                TaskItem(task, onAdvance = { viewModel.advanceStatus(task) }, onDelete = { viewModel.deleteTask(task) })
            }
        }
    }
}

@Composable
fun TaskItem(task: Task, onAdvance: () -> Unit, onDelete: () -> Unit) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Title: ${task.title}")
            Text("Status: ${task.status}")

            Row(modifier = Modifier.padding(top = 8.dp)) {
                if (task.status == TaskStatus.PENDING) {
                    Button(onClick = onAdvance) { Text("Start") }
                } else if (task.status == TaskStatus.IN_PROGRESS) {
                    Button(onClick = onAdvance) { Text("Complete") }
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = onDelete, colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.error)) {
                    Text("Delete")
                }
            }
        }
    }
}

