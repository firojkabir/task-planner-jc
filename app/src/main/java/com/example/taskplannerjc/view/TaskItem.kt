package com.example.taskplannerjc.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.taskplannerjc.model.Task
import com.example.taskplannerjc.model.TaskState

@Composable
fun TaskItem(task: Task, onAdvance: () -> Unit, onDelete: () -> Unit) {
    Card(
        elevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(text = task.title, style = MaterialTheme.typography.h6)
            Text(text = "Status: ${task.state}")

            Row(modifier = Modifier.padding(top = 8.dp)) {
                when (task.state) {
                    TaskState.Pending -> Button(
                        onClick = onAdvance,
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.Blue,
                            contentColor = Color.White
                        )
                    ) { Text("Start") }
                    TaskState.InProgress -> Button(
                        onClick = onAdvance,
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.Blue,
                            contentColor = Color.White
                        )
                    ) { Text("Complete") }
                    TaskState.Complete -> Button(
                        enabled = false, onClick = {},
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.Blue,
                            contentColor = Color.White
                        )
                    ) { Text("Done") }
                }

                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = onDelete,
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Red,
                        contentColor = Color.White
                    )
                ) { Text("Delete") }
            }
        }
    }
}