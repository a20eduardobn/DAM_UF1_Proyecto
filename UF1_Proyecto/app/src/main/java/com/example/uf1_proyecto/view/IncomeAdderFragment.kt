package com.example.uf1_proyecto.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.uf1_proyecto.MainActivity
import com.example.uf1_proyecto.R
import com.example.uf1_proyecto.databinding.FragmentIncomeAdderBinding
import com.example.uf1_proyecto.model.Registro
import com.example.uf1_proyecto.model.RegistrosViewModel
import java.text.SimpleDateFormat
import java.util.Calendar

class IncomeAdderFragment : Fragment() {
    private var _binding: FragmentIncomeAdderBinding? = null
    private val binding get() = _binding!!

    private lateinit var registrosViewModel: RegistrosViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentIncomeAdderBinding.inflate(inflater, container, false)
        val view = binding.root
        registrosViewModel = (activity as MainActivity).registrosViewModel
        binding.fabSendIncome.setOnClickListener {
            leerDatos()
        }

        // Devolver la vista
        return view
    }

    //Funcionalidad similar al de ExpenseAdder
    private fun leerDatos() {
        if (binding.nameEditTextIncome.text.toString().isEmpty() ||
            binding.amountEditTextIncome.text.toString().isEmpty() ||
            binding.descriptionEditTextIncome.text.toString().isEmpty()
        ) {
            val mensaje = context?.getString(R.string.toast_form_empty)
            Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show()
        } else {
            val name = binding.nameEditTextIncome.text.toString()
            val amount = binding.amountEditTextIncome.text.toString().toDoubleOrNull() ?: 0.0
            val description = binding.descriptionEditTextIncome.text.toString()

            val registro = Registro(
                id = 0,
                name = name,
                description = description,
                amount = amount,
                category = "Income",
                date = obtenerFecha()
            )

            registrosViewModel.insertRegistry(registro)

            findNavController().navigateUp()
        }
    }

    @SuppressLint("SimpleDateFormat")
    fun obtenerFecha(): String{
        val currentTime = Calendar.getInstance().time
        val dateFormat = SimpleDateFormat("dd/MM/yyyy")
        return dateFormat.format(currentTime)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}