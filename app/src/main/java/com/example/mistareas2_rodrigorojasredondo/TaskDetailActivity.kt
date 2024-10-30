package com.example.mistareas2_rodrigorojasredondo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class TaskDetailActivity : ComponentActivity() {

    private lateinit var repository: TaskRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        repository = TaskRepository(this)

        val taskId = intent.getLongExtra("TASK_ID", -1)

        setContent {
            val task = repository.getTasks().find { it.id == taskId }
            task?.let {
                TaskDetailScreen(
                    task = it,
                    onBack = { finish() },
                    onMarkAsCompleted = {
                        repository.markTaskAsCompleted(taskId)
                        finish() // Cierra la actividad después de marcar como hecha
                    }
                )
            }
        }
    }
}

@Composable
fun TaskDetailScreen(task: Task, onBack: () -> Unit, onMarkAsCompleted: () -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = task.name, style = MaterialTheme.typography.headlineMedium)
        Text(text = "Descripción: ${task.description}")
        Text(text = "Fecha: ${task.date}")
        Text(text = "Prioridad: ${task.priority}")
        Text(text = "Coste: ${task.cost} €")

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            Button(onClick = onBack) {
                Text("Volver")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = onMarkAsCompleted) {
                Text("Marcar como Hecha")
            }
        }
    }
}



