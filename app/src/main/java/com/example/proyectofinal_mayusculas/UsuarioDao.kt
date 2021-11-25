package com.example.proyectofinal_mayusculas

import androidx.room.*

@Dao
interface UsuarioDao {

    @Query("SELECT * from Usuario")
    suspend fun getAllUsuarios(): List<Usuario>?

    @Query("SELECT * from Usuario order by nombre ASC")
    suspend fun getAllNameAsc(): List<Usuario>?

    @Query("SELECT * from Usuario order by calif ASC")
    suspend fun getAllScoreAsc(): List<Usuario>?

    @Query("SELECT * from Usuario order by calif DESC LIMIT 10")
    suspend fun get10ScoreAsc(): List<Usuario>?

    @Query("SELECT * from Usuario where id = :id")
    suspend fun getByID(id: Int): Usuario?

    @Query("SELECT * from Usuario where nombre = :nombre")
    suspend fun getByName(nombre: String): List<Usuario>?

    @Query("SELECT * from Usuario where calif = :calif")
    suspend fun getByScore(calif: Int): List<Usuario>?



    @Insert
    suspend fun addUsuario(usuario: Usuario): Long

    @Update
    suspend fun actualizarUsuario(usuario: Usuario)

    @Delete
    suspend fun eliminarUsuario(usuario: Usuario)



    @Query("DELETE FROM Usuario")
    suspend fun NukeUsuarios()
}