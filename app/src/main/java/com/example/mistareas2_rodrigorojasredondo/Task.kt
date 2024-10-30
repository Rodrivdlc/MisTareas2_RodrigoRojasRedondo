package com.example.mistareas2_rodrigorojasredondo

data class Task(
    val id: Long = 0,
    val name: String,
    val description: String,
    val date: String,
    val priority: String,
    val cost: Double,
    val isCompleted: Boolean = false
)

