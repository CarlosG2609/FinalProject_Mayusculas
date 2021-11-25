package com.example.proyectofinal_mayusculas

import android.app.Application

class UsuarioApp: Application() {

    val database: AppDatabase by lazy{
        AppDatabase.getDatabase(this)
    }

}