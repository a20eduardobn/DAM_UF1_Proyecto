package com.example.uf1_proyecto

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity

class ExpenseAdderFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity?)!!.supportActionBar!!.title =
            getString(R.string.expense_add_text)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_expense_adder, container, false)
    }
}