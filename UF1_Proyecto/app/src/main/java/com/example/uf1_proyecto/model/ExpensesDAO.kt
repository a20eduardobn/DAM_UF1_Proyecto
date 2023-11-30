package com.example.uf1_proyecto.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ExpensesDAO {

    @Query("SELECT * FROM expenses")
    fun getAll(): List<Expense>

    @Query("SELECT * FROM expenses WHERE id IN (:expenseIds)")
    fun loadAllByIds(expenseIds: IntArray): List<Expense>

    @Query("SELECT * FROM expenses WHERE id IN (:id) LIMIT 1")
    fun findById(id: Int): Expense

    @Insert
    fun insertAll(vararg expenses: Expense)

    @Delete
    fun delete(expense: Expense)

}