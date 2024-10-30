package com.example.mistareas2_rodrigorojasredondo

import android.content.ContentValues
import android.content.Context

class TaskRepository(context: Context) {
    private val dbHelper = TaskDatabaseHelper(context)

    fun addTask(task: Task) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(TaskDatabaseHelper.COLUMN_NAME, task.name)
            put(TaskDatabaseHelper.COLUMN_DESCRIPTION, task.description)
            put(TaskDatabaseHelper.COLUMN_DATE, task.date)
            put(TaskDatabaseHelper.COLUMN_PRIORITY, task.priority)
            put(TaskDatabaseHelper.COLUMN_COST, task.cost)
            put(TaskDatabaseHelper.COLUMN_COMPLETED, if (task.isCompleted) 1 else 0)
        }
        db.insert(TaskDatabaseHelper.TABLE_NAME, null, values)
    }

    fun getTasks(isCompleted: Boolean? = null): List<Task> {
        val db = dbHelper.readableDatabase
        val selection = isCompleted?.let { "${TaskDatabaseHelper.COLUMN_COMPLETED} = ?" }
        val selectionArgs = isCompleted?.let { arrayOf(if (it) "1" else "0") }
        val cursor = db.query(TaskDatabaseHelper.TABLE_NAME, null, selection, selectionArgs, null, null, null)

        val tasks = mutableListOf<Task>()
        with(cursor) {
            while (moveToNext()) {
                val task = Task(
                    id = getLong(getColumnIndexOrThrow(TaskDatabaseHelper.COLUMN_ID)),
                    name = getString(getColumnIndexOrThrow(TaskDatabaseHelper.COLUMN_NAME)),
                    description = getString(getColumnIndexOrThrow(TaskDatabaseHelper.COLUMN_DESCRIPTION)),
                    date = getString(getColumnIndexOrThrow(TaskDatabaseHelper.COLUMN_DATE)),
                    priority = getString(getColumnIndexOrThrow(TaskDatabaseHelper.COLUMN_PRIORITY)),
                    cost = getDouble(getColumnIndexOrThrow(TaskDatabaseHelper.COLUMN_COST)),
                    isCompleted = getInt(getColumnIndexOrThrow(TaskDatabaseHelper.COLUMN_COMPLETED)) == 1
                )
                tasks.add(task)
            }
        }
        cursor.close()
        return tasks
    }
}
