package com.example.uf1_proyecto.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Registro::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun expensesDao(): RegistrosDAO

    companion object {
        // Implementacion del patron Singleton para evitar multiples instancias de la BD
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            //Comprobamos si la instancia es nula, si lo es la creamos
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "word_database"
                ).build()
                INSTANCE = instance
                // devolvemos la instancia
                instance
            }
        }
    }
}