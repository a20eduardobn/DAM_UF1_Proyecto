package com.example.uf1_proyecto.model

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

//Esta clase actuará como entity para Room, cada instancia representa una fila en la BD
@androidx.room.Entity(tableName = "registries")
data class Registro(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "amount") val amount: Double,
    @ColumnInfo(name = "category") val category: String,
    @ColumnInfo(name = "date") val date: String
)