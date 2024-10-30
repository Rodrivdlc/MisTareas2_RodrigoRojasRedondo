package com.example.mistareas2_rodrigorojasredondo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

class RegisterTaskActivity : ComponentActivity() {

    private lateinit var repository: TaskRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        repository = TaskRepository(this)

        setContent {
            RegisterTaskScreen(onRegister = { task ->
                repository.addTask(task)
                finish() // Cierra la actividad después de registrar la tarea
            })
        }
    }
}

@Composable
fun RegisterTaskScreen(onRegister: (Task) -> Unit) {
    val context = LocalContext.current // Obtiene el contexto de la actividad actual

    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var priority by remember { mutableStateOf("") }
    var cost by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(value = name, onValueChange = { name = it }, label = { Text("Nombre") })
        TextField(value = description, onValueChange = { description = it }, label = { Text("Descripción") })
        TextField(value = date, onValueChange = { date = it }, label = { Text("Fecha") })
        TextField(value = priority, onValueChange = { priority = it }, label = { Text("Prioridad") })
        TextField(value = cost, onValueChange = { cost = it }, label = { Text("Coste") })

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            Button(
                onClick = {
                    onRegister(Task(name = name, description = description, date = date, priority = priority, cost = cost.toDoubleOrNull() ?: 0.0))
                    (context as? ComponentActivity)?.finish() // Llama a finish() en la actividad
                }
            ) {
                Text("Registrar")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = { (context as? ComponentActivity)?.finish() } // Cierra la actividad si el usuario cancela
            ) {
                Text("Cancelar")
            }
        }
    }
}
