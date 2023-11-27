package com.example.uf1_proyecto.Model

// Esta clase es un singleton, es decir, solo puede haber una instancia de ella
class ExpensesDAO private constructor(){

    // Companion object permite acceder a los métodos y propiedades de la clase
    // sin necesidad de crear un objeto
    companion object {
        // Esta es la instancia única de la clase
        private var instance: ExpensesDAO? = null

        // Este método se llama para obtener la instancia única
        fun getInstance(): ExpensesDAO {
            if (instance == null) {
                instance = ExpensesDAO()
            }
            return instance!!
        }
    }
}