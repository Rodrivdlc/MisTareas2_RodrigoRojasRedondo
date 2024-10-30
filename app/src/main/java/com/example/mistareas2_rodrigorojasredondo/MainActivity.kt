package com.example.mistareas2_rodrigorojasredondo

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {

    private lateinit var repository: TaskRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        repository = TaskRepository(this)

        setContent {
            TaskListScreen(
                repository = repository,
                onTaskClick = { taskId ->
                    // Navegar a TaskDetailActivity con el ID de la tarea seleccionada
                    val intent = Intent(this, TaskDetailActivity::class.java).apply {
                        putExtra("TASK_ID", taskId)
                    }
                    startActivity(intent)
                },
                onAddTaskClick = {
                    // Navegar a RegisterTaskActivity para agregar una nueva tarea
                    val intent = Intent(this, RegisterTaskActivity::class.java)
                    startActivity(intent)
                }
            )
        }
    }

    override fun onResume() {
        super.onResume()
        // Recargar la lista de tareas al volver a la pantalla
        setContent {
            TaskListScreen(
                repository = repository,
                onTaskClick = { taskId ->
                    val intent = Intent(this, TaskDetailActivity::class.java).apply {
                        putExtra("TASK_ID", taskId)
                    }
                    startActivity(intent)
                },
                onAddTaskClick = {
                    val intent = Intent(this, RegisterTaskActivity::class.java)
                    startActivity(intent)
                }
            )
        }
    }
}

@Composable
fun TaskListScreen(repository: TaskRepository, onTaskClick: (Long) -> Unit, onAddTaskClick: () -> Unit) {
    var isCompleted by remember { mutableStateOf(false) }
    var tasks by remember { mutableStateOf(repository.getTasks(isCompleted)) }

    Column(modifier = Modifier.padding(16.dp)) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Button(onClick = {
                isCompleted = false
                tasks = repository.getTasks(isCompleted) // Actualiza la lista de tareas pendientes
            }) {
                Text("Pendientes")
            }
            Button(onClick = {
                isCompleted = true
                tasks = repository.getTasks(isCompleted) // Actualiza la lista de tareas hechas
            }) {
                Text("Hechas")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botón para agregar una nueva tarea
        Button(onClick = onAddTaskClick, modifier = Modifier.fillMaxWidth()) {
            Text("Añadir Nueva Tarea")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Muestra la lista de tareas desde el inicio
        LazyColumn {
            items(tasks) { task ->
                Text(
                    text = task.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onTaskClick(task.id) }
                        .padding(8.dp)
                )
                Divider()
            }
        }
    }
}

