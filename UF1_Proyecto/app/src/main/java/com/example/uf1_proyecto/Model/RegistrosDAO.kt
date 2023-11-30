package com.example.uf1_proyecto.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RegistrosDAO {


    @Query("SELECT * FROM registries")
    fun getAll(): Flow<List<Registro>>

    @Query("SELECT * FROM registries WHERE id IN (:expenseIds)")
    fun loadAllByIds(expenseIds: IntArray): List<Registro>

    @Query("SELECT * FROM registries WHERE id IN (:id) LIMIT 1")
    fun findById(id: Int): Registro

    @Insert
    fun insertAll(vararg expens: Registro)

    @Delete
    fun delete(registro: Registro)

}