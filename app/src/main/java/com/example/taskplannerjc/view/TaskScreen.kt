package com.example.taskplannerjc.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.taskplannerjc.model.Task
import com.example.taskplannerjc.model.TaskState
import com.example.taskplannerjc.viewmodel.TaskViewModel

@Composable
fun TaskScreen(viewModel: TaskViewModel) {
    var newTaskTitle by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        Row {
            TextField(
                value = newTaskTitle,
                onValueChange = { newTaskTitle = it },
                label = { Text("Task Title") },
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = {
                if (newTaskTitle.isNotBlank()) {
                    viewModel.addTask(newTaskTitle)
                    newTaskTitle = ""
                }
            }) {
                Text("Add")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(viewModel.tasks) { task ->
                TaskItem(task = task, onAdvanceState = { viewModel.advanceTaskState(task) })
            }
        }
    }
}

@Composable
fun TaskItem(task: Task, onAdvanceState: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        elevation = 4.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = task.title, style = MaterialTheme.typography.h6)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Status: ${task.state}", style = MaterialTheme.typography.body1)

            Spacer(modifier = Modifier.height(8.dp))

            Row {
                when (task.state) {
                    TaskState.Pending -> {
                        Button(onClick = onAdvanceState) {
                            Text("Start Task")
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Button(onClick = {}, enabled = false) {
                            Text("Complete")
                        }
                    }
                    TaskState.InProgress -> {
                        Button(onClick = onAdvanceState) {
                            Text("Complete")
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Button(onClick = {}, enabled = false) {
                            Text("Restart")
                        }
                    }
                    TaskState.Complete -> {
                        Button(onClick = {}, enabled = false) {
                            Text("Completed")
                        }
                    }
                }
            }
        }
    }
}

