package com.example.uf1_proyecto.model

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class RegistrosRepository (private val registrosDAO: RegistrosDAO) {


    val todosRegistros: Flow<List<Registro>> = registrosDAO.getAll()

    //Nos aseguramos que las operaciones sean en un hilo secundario
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertAll(registro: Registro) {
        registrosDAO.insertAll(registro)
    }
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun findById(id: Int): Registro {
        return registrosDAO.findById(id)
    }
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun delete(registro: Registro) {
        registrosDAO.delete(registro)
    }
}