package com.example.uf1_proyecto.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.uf1_proyecto.MainActivity
import com.example.uf1_proyecto.R
import com.example.uf1_proyecto.databinding.FragmentExpenseAdderBinding
import com.example.uf1_proyecto.model.Registro
import com.example.uf1_proyecto.model.RegistrosViewModel
import java.text.SimpleDateFormat
import java.util.Calendar

class ExpenseAdderFragment : Fragment() {
    private var _binding: FragmentExpenseAdderBinding? = null
    private val binding get() = _binding!!

    private lateinit var registrosViewModel: RegistrosViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentExpenseAdderBinding.inflate(inflater, container, false)
        val view = binding.root
        registrosViewModel = (activity as MainActivity).registrosViewModel
        binding.fabSendExpense.setOnClickListener {
            leerDatos()
        }

        // Devolver la vista
        return view
    }

    private fun leerDatos() {
        // Si hay campos vacíos se muestra un Toast
        if (binding.nameEditTextExpense.text.toString().isEmpty() ||
            binding.amountEditTextExpense.text.toString().isEmpty() ||
            binding.descriptionEditTextExpense.text.toString().isEmpty() ||
            binding.chipGroupExpenseType.checkedChipId == -1
        ) {
            val mensaje = context?.getString(R.string.toast_form_empty)
            Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show()
        } else {
            // Si no hay campos vacíos, se envían los datos al ViewModel
            val name = binding.nameEditTextExpense.text.toString()
            val amount = binding.amountEditTextExpense.text.toString().toDoubleOrNull() ?: 0.0
            val description = binding.descriptionEditTextExpense.text.toString()
            val categoryId = binding.chipGroupExpenseType.checkedChipId

            val category = when (categoryId) {
                R.id.chipShopping -> "Shopping"
                R.id.chipHouse -> "House"
                R.id.chipVehicle -> "Vehicle"
                R.id.chipOther -> "Other"
                else -> "DefaultCategory"
            }

            val registro = Registro(
                id = 0,
                name = name,
                description = description,
                amount = amount,
                category = category,
                date = obtenerFecha()
            )

            registrosViewModel.insertRegistry(registro)

            findNavController().navigateUp()
        }
    }

    @SuppressLint("SimpleDateFormat")
    fun obtenerFecha(): String{
        val currentTime = Calendar.getInstance().time
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        return dateFormat.format(currentTime)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}