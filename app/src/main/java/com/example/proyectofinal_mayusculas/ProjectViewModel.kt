package com.example.proyectofinal_mayusculas

import androidx.lifecycle.ViewModel

class ProjectViewModel: ViewModel() {
    private val _todos= mutableListOf<Todo>()

    val todo= _todos

    // Aquí puedo agregar funciones para actualizar la base de datos
}