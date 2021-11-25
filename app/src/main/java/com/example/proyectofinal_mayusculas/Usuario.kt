package com.example.proyectofinal_mayusculas

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Usuario(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @NonNull var nombre: String,
    @NonNull var calif: Int,
    @NonNull var fecha: String
){
    constructor(nombre: String, calif: Int, fecha: String): this(0,nombre, calif, fecha)
}