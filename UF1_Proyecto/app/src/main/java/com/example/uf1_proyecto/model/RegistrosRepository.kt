package com.example.uf1_proyecto.model

import androidx.annotation.WorkerThread
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class RegistrosRepository(private val registrosDAO: RegistrosDAO) {

    val todosRegistros: Flow<List<Registro>> = registrosDAO.getAll()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertAll(registro: Registro) {
        withContext(Dispatchers.IO) {
            registrosDAO.insertAll(registro)
        }
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun findById(id: Int): Registro {
        return withContext(Dispatchers.IO) {
            registrosDAO.findById(id)
        }
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun delete(registro: Registro) {
        withContext(Dispatchers.IO) {
            registrosDAO.delete(registro)
        }
    }
}