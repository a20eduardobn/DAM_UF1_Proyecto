package com.example.uf1_proyecto.controller

import android.view.View
import com.example.uf1_proyecto.model.Expense
import com.example.uf1_proyecto.model.ExpensesDAO

//Clase que se encarga de gestionar la logica de la aplicacion
class Controller(private val expensesDAO: ExpensesDAO) {

    //Funcion que se encarga de lanzar recoger los datos del formulario
    // y lanzar la funcion de añadir el registro
    fun insertExpense(name: String, description: String, amount: Double, category: View) {
        val newExpense = Expense(0, name, description, amount, category.toString(), "")
        expensesDAO.insertAll(newExpense)
    }

    fun insertExpense(expense: Expense) {
        expensesDAO.insertAll(expense)
    }

    fun getAllExpenses(): List<Expense> {
        return expensesDAO.getAll()
    }

    fun getExpenseById(id: Int): Expense {
        return expensesDAO.findById(id)
    }

    fun deleteExpense(expense: Expense) {
        expensesDAO.delete(expense)
    }
}