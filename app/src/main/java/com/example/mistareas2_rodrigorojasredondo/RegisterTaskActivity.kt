package com.example.mistareas2_rodrigorojasredondo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
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
    val context = LocalContext.current

    var name by remember { mutableStateOf(TextFieldValue("")) }
    var description by remember { mutableStateOf(TextFieldValue("")) }
    var date by remember { mutableStateOf(TextFieldValue("")) }
    var priority by remember { mutableStateOf(TextFieldValue("")) }
    var cost by remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black) // Fondo negro general
            .padding(16.dp)
    ) {
        TextField(value = name, onValueChange = { name = it }, label = { Text("Nombre") })
        TextField(value = description, onValueChange = { description = it }, label = { Text("Descripción") })
        TextField(value = date, onValueChange = { date = it }, label = { Text("Fecha") })
        TextField(value = priority, onValueChange = { priority = it }, label = { Text("Prioridad") })
        TextField(value = cost, onValueChange = { cost = it }, label = { Text("Coste") })

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            Button(
                onClick = {
                    onRegister(
                        Task(
                            name = name.text,
                            description = description.text,
                            date = date.text,
                            priority = priority.text,
                            cost = cost.text.toDoubleOrNull() ?: 0.0
                        )
                    )
                    (context as? ComponentActivity)?.finish()
                }
            ) {
                Text("Registrar")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = { (context as? ComponentActivity)?.finish() }
            ) {
                Text("Cancelar")
            }
        }
    }
}



