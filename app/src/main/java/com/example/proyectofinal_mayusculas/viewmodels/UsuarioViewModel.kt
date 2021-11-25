package com.example.proyectofinal_mayusculas.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.proyectofinal_mayusculas.Usuario
import com.example.proyectofinal_mayusculas.UsuarioDao
import java.lang.IllegalArgumentException

class UsuarioViewModel(private val usuarioDao: UsuarioDao): ViewModel() {

    // Este ViewModel sirve para poder utilizar y acceder a la base de datos de Room en TODOS los
    // fragments de la aplicación.
    suspend fun getAllUsuarios(): List<Usuario>? = usuarioDao.getAllUsuarios()
    suspend fun getAllNameAsc(): List<Usuario>? = usuarioDao.getAllNameAsc()
    suspend fun getAllScoreAsc(): List<Usuario>? = usuarioDao.getAllScoreAsc()
    suspend fun get10ScoreAsc(): List<Usuario>? = usuarioDao.get10ScoreAsc()
    suspend fun getByID(id: Int): Usuario? = usuarioDao.getByID(id)
    suspend fun getByName(nombre: String): List<Usuario>? = usuarioDao.getByName(nombre)
    suspend fun getByScore(calif: Int): List<Usuario>? = usuarioDao.getByScore(calif)
    suspend fun addUsuario(usuario: Usuario): Long = usuarioDao.addUsuario(usuario)
    suspend fun actualizarUsuario(usuario: Usuario) = usuarioDao.actualizarUsuario(usuario)
    suspend fun eliminarUsuario(usuario: Usuario) = usuarioDao.eliminarUsuario(usuario)
    suspend fun NukeUsuarios() = usuarioDao.NukeUsuarios()
}

class UsuariosViewModelFactory(private val usuarioDao: UsuarioDao): ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UsuarioViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return UsuarioViewModel(usuarioDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}