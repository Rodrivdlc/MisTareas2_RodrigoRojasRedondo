# MisTareas2_RodrigoRojasRedondo

MisTareas2:
https://github.com/Rodrivdlc/MisTareas2_RodrigoRojasRedondo.git

## Esta es la tercera aplicación de tres que incluye el ejercicio Prueba de Programación Android 1:

otros ejercicios:

MisTareas1: https://github.com/Rodrivdlc/MisTareas1_RodrigoRojasRedondo.git

ListaCompra: https://github.com/Rodrivdlc/ListaCompra_RodrigoRojasRedondo.git

## Descripción

MisTareas2_RodrigoRojasRedondo es una aplicación de Android diseñada para ayudar a los usuarios a gestionar sus tareas en tres pantallas interactivas: una para registrar nuevas tareas, otra para listar las tareas filtradas por estado (hechas o pendientes), y otra para ver los detalles de una tarea específica. La aplicación utiliza SQLite para persistencia local, de modo que las tareas se mantienen almacenadas en el dispositivo.

## Estructura de la Aplicación
Pantalla de Registro de Tareas: Permite al usuario añadir una tarea con los siguientes detalles:
Nombre
Descripción
Fecha
Prioridad
Coste
Pantalla de Listado de Tareas: Muestra la lista de tareas, con opciones para:
Filtrar por Tareas Pendientes o Tareas Hechas.
Añadir nuevas tareas mediante un botón que dirige a la pantalla de registro.
Acceder a los detalles de una tarea específica.
Pantalla de Detalles de Tarea: Visualiza los detalles de la tarea seleccionada y permite:
Marcar la tarea como hecha.
Volver al listado de tareas.
Características Técnicas
Persistencia con SQLite: La aplicación utiliza una base de datos SQLite para almacenar de manera persistente la información de las tareas.
Interfaz con Jetpack Compose: Toda la interfaz de usuario se ha desarrollado usando Jetpack Compose, con un tema oscuro consistente en fondo negro y texto blanco.
Navegación entre Activities: La aplicación utiliza tres Activities (MainActivity, RegisterTaskActivity y TaskDetailActivity) para estructurar el flujo de navegación de tareas.
