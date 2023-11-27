package com.example.uf1_proyecto.Controller

import com.example.uf1_proyecto.Model.ExpensesDAO

//Clase que se encarga de gestionar la logica de la aplicacion
class Controller {
    //Se obtiene la instancia del DAO que se encarga de gestionar los datos
    private val expensesInstance = ExpensesDAO.getInstance()

    //Funcion que se encarga de lanzar recoger los datos del formulario
    // y lanzar la funcion de añadir el registro
    fun launchFormSubmission(
        toString: String,
        toString1: String,
        toDouble: Double,
        checkedChipId: Int
    ) {
    }
}