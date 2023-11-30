package com.example.uf1_proyecto.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.get
import androidx.fragment.app.Fragment
import com.example.uf1_proyecto.controller.Controller
import com.example.uf1_proyecto.R
import com.example.uf1_proyecto.databinding.FragmentExpenseAdderBinding
import com.example.uf1_proyecto.databinding.FragmentIncomeAdderBinding

class IncomeAdderFragment : Fragment() {
    private var _binding: FragmentIncomeAdderBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentIncomeAdderBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.fabSendExpense.setOnClickListener {
            leerDatos()
        }

        // Devolver la vista
        return view
    }

    fun leerDatos() {
        //Si hay campos vacios se muestra un toast
        if (binding.nameEditTextExpense.text.toString().isEmpty() ||
            binding.amountEditTextExpense.text.toString().isEmpty() ||
            binding.descriptionEditTextExpense.text.toString().isEmpty()
        ) {
            val mensaje = context?.getString(R.string.toast_form_empty)
            Toast.makeText(context, mensaje , Toast.LENGTH_SHORT).show()
        } else {
            //Si no hay campos vacios se envian los datos al controlador
//            Controller(requireContext()).ins(binding.nameEditTextExpense.text.toString(),
//                binding.descriptionEditTextExpense.text.toString(),
//                binding.amountEditTextExpense.text.toString().toDouble())
        }
    }
}