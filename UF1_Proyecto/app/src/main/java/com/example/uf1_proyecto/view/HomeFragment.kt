package com.example.uf1_proyecto.view

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.uf1_proyecto.R
import com.example.uf1_proyecto.databinding.FragmentHomeBinding
import com.example.uf1_proyecto.model.Registro
import com.example.uf1_proyecto.model.RegistrosAdapter
import com.example.uf1_proyecto.model.RegistrosApplication
import com.example.uf1_proyecto.model.RegistrosViewModel
import com.example.uf1_proyecto.model.RegistrosViewModelFactory
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartModel
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartType
import com.github.aachartmodel.aainfographics.aachartcreator.AASeriesElement
import java.text.SimpleDateFormat
import java.util.Calendar

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val newWordActivityRequestCode = 1

    private val registrosViewModel: RegistrosViewModel by viewModels {
        RegistrosViewModelFactory((requireActivity().application as RegistrosApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.fabAddTransaction.setOnClickListener {
            val intent = Intent(requireContext(), ExpenseAdderFragment::class.java)
            startActivityForResult(intent, newWordActivityRequestCode)
        }

        // Creando el chart a partir del modelo de datos
        binding.mainChartView.aa_drawChartWithChartModel(createChartModel())

        // Devolver la vista
        val adapter = RegistrosAdapter()
        // Configuramos el observador para el viewmodel
        registrosViewModel.todosRegistros.observe(viewLifecycleOwner, Observer { registros ->
            registros?.let { adapter.submitList(it) }
        })

        return view
    }

    // Funcion que crea el modelo de datos para el chart
    private fun createChartModel(): AAChartModel {
        // Convertir los colores de colors.xml a strings para poder usarlos en el chart
        val colorPrimary = "#" + Integer.toHexString(
            ContextCompat.getColor(
                requireContext(),
                R.color.colorPrimaryLight
            )
        ).substring(2)
        val colorAccentPrimary = "#" + Integer.toHexString(
            ContextCompat.getColor(
                requireContext(),
                R.color.accent_color_primary
            )
        ).substring(2)

        // Modelo de datos para el chart
        return AAChartModel()
            .chartType(AAChartType.Column)
            .dataLabelsEnabled(false)
            .legendEnabled(false)
            .axesTextColor("#FFFFFF")
            .colorsTheme(arrayOf(colorAccentPrimary))
            .backgroundColor(colorPrimary)
            .categories(arrayOf(getString(R.string.expenseWord), getString(R.string.incomeWord)))
            .series(
                arrayOf(
                    AASeriesElement()
                        .name("Temperatura")
                        .data(arrayOf(420, 140))
                )
            )
            .yAxisTitle("")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == newWordActivityRequestCode && resultCode == Activity.RESULT_OK) {
            data?.getStringExtra(ExpenseAdderFragment.EXTRA_REPLY)?.let {serializedData ->
                //Extrae
                val datos = serializedData.split(",")
                val registro = Registro(
                    id = 0, // Deberías asignar un valor adecuado al id según tu lógica
                    name = datos[0],
                    description = datos[2],
                    amount = datos[1].toDoubleOrNull() ?: 0.0,
                    category = datos[3],
                    date = obtenerFecha()
                )
                registrosViewModel.insertRegistry(registro)
            }
        } else {
            Toast.makeText(
                requireContext(),
                R.string.toast_empty_notsaved,
                Toast.LENGTH_LONG
            ).show()
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
        // Limpiar referencias para evitar leaks
        _binding = null
    }
}