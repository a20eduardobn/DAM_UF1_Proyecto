package com.example.uf1_proyecto.model

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class RegistrosApplication: Application() {
    val database by lazy { AppDatabase.getInstance(this) }
    val repository by lazy { RegistrosRepository(database.registrosDAO()) }
}