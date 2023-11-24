package com.example.uf1_proyecto

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.uf1_proyecto.databinding.FragmentExpenseAdderBinding
import com.example.uf1_proyecto.databinding.FragmentHomeBinding

class ExpenseAdderFragment : Fragment() {
    private var _binding: FragmentExpenseAdderBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentExpenseAdderBinding.inflate(inflater, container, false)
        val view = binding.root

        // Devolver la vista
        return view
    }
}