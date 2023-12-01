package com.example.uf1_proyecto.model

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class RegistrosApplication: Application() {
    val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { AppDatabase.getInstance(this, applicationScope) }
    val repository by lazy { RegistrosRepository(database.expensesDao()) }
}