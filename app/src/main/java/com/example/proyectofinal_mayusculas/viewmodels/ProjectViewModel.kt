package com.example.proyectofinal_mayusculas.viewmodels

import androidx.lifecycle.ViewModel
import com.example.proyectofinal_mayusculas.Todo

class ProjectViewModel: ViewModel() {

    // Este ViewModel sirve para almacenar valores entre fragments que se obtienen de editText's o
    // de botones en el programa.
    private var _name: String= ""
    private var _score: Int= 0
    private val _todos= mutableListOf<Todo>()

    val name get() = _name
    val score get() = _score
    val todo= _todos

    // Aquí puedo agregar funciones para actualizar la base de datos
    fun changeName(nuevoNombre: String){
        _name= nuevoNombre
    }
    fun changeScore(nuevoScore: Int){
        _score= nuevoScore
    }
}