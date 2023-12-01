package com.example.uf1_proyecto.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.uf1_proyecto.controller.Controller
import com.example.uf1_proyecto.R
import com.example.uf1_proyecto.databinding.FragmentExpenseAdderBinding
import com.example.uf1_proyecto.model.RegistrosViewModel

class ExpenseAdderFragment : Fragment() {
    private var _binding: FragmentExpenseAdderBinding? = null
    private val binding get() = _binding!!

    companion object {
        const val EXTRA_REPLY = "com.example.uf1_proyecto.REPLY"
    }

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
            val replyIntent = Intent()
            //Concatenamos todos los resultados para enviarlos
            val resultado = binding.nameEditTextExpense.text.toString()+","+
                    binding.amountEditTextExpense.text.toString()+","+
                    binding.descriptionEditTextExpense.text.toString()+","+
                    binding.chipGroupExpenseType.checkedChipId

            replyIntent.putExtra(EXTRA_REPLY, resultado)
            requireActivity().setResult(Activity.RESULT_OK, replyIntent)
            requireActivity().finish()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}