package com.example.uf1_proyecto.model


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class RegistrosViewModel(private val repository:RegistrosRepository) : ViewModel() {

    val todosRegistros: LiveData<List<Registro>> = repository.todosRegistros.asLiveData()

    fun insertRegistry(name: String, description: String, amount: Double, category: String) {
        viewModelScope.launch {
            val registro = Registro(0, name, description, amount, category, "")
            repository.insertAll(registro)
        }
    }

    fun insertRegistry(registro: Registro) {
        viewModelScope.launch {
            repository.insertAll(registro)
        }
    }
}

//Esto asegurará tener la instancia del ViewModel correcta
class RegistrosViewModelFactory(private val repository: RegistrosRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegistrosViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RegistrosViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
