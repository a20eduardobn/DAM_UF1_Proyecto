package com.example.uf1_proyecto.GUI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.uf1_proyecto.Controller.Controller
import com.example.uf1_proyecto.R
import com.example.uf1_proyecto.databinding.FragmentExpenseAdderBinding

class ExpenseAdderFragment : Fragment() {
    private var _binding: FragmentExpenseAdderBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentExpenseAdderBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.fabSendExpense.setOnClickListener {
            leerDatos()
        }

        // Devolver la vista
        return view
    }

    fun leerDatos() {
        //Si hay campos vacios obligatorios se muestra un toast
        if (binding.nameEditTextExpense.text.toString().isEmpty() ||
            binding.amountEditTextExpense.text.toString().isEmpty() ||
            binding.descriptionEditTextExpense.text.toString().isEmpty()
        ) {
            val mensaje = context?.getString(R.string.toast_form_empty)
            Toast.makeText(context, mensaje , Toast.LENGTH_SHORT).show()
        } else if(binding.chipGroupExpenseType.checkedChipId==-1){
            val mensaje = context?.getString(R.string.toast_form_empty)
            Toast.makeText(context, mensaje , Toast.LENGTH_SHORT).show()
        } else {
            //Si no hay campos vacios se envian los datos al controlador
            Controller().launchFormSubmission(binding.nameEditTextExpense.text.toString(),
                binding.descriptionEditTextExpense.text.toString(),
                binding.amountEditTextExpense.text.toString().toDouble(),
                binding.chipGroupExpenseType.checkedChipId)
        }
    }
}