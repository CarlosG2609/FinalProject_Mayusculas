package com.example.proyectofinal_mayusculas

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Usuario::class], version = 1)
abstract class AppDatabase : RoomDatabase(){

    abstract fun DaoPrincipal(): UsuarioDao

    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this){
                // Init Room Database
                val instance= Room.databaseBuilder( context,
                                                    AppDatabase::class.java,
                                                    "db_proyecto").build()
                INSTANCE= instance

                instance
            }
        }
    }
}