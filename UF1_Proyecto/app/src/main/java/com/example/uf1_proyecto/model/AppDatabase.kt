package com.example.uf1_proyecto.model

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Expense::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun expensesDao(): ExpensesDAO
}