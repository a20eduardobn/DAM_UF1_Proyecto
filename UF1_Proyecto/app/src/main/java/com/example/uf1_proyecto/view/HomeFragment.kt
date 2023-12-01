package com.example.uf1_proyecto.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.uf1_proyecto.MainActivity
import com.example.uf1_proyecto.R
import com.example.uf1_proyecto.databinding.FragmentHomeBinding
import com.example.uf1_proyecto.model.Registro
import com.example.uf1_proyecto.model.RegistrosAdapter
import com.example.uf1_proyecto.model.RegistrosViewModel
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartModel
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartType
import com.github.aachartmodel.aainfographics.aachartcreator.AASeriesElement

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var registrosViewModel: RegistrosViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        registrosViewModel = (activity as MainActivity).registrosViewModel

        binding.fabAddTransaction.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_expenseAdderFragment)
        }


        // Devolver la vista
        val adapter = RegistrosAdapter()
        // Configuramos el observador para el viewmodel
        registrosViewModel.todosRegistros.observe(viewLifecycleOwner, Observer { registros ->
            registros?.let {
                adapter.submitList(it)
                binding.mainChartView.aa_drawChartWithChartModel(createChartModel(it))
            }
        })

        return view
    }

    // Funcion que crea el modelo de datos para el chart
    private fun createChartModel(registros: List<Registro>): AAChartModel {
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

        // Obtener los datos reales de la base de datos
        val expenseTotal = registros.sumByDouble { if (it.category == "Expense") it.amount else 0.0 }
        val incomeTotal = registros.sumByDouble { if (it.category == "Income") it.amount else 0.0 }

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
                        .name("Total")
                        .data(arrayOf(expenseTotal, incomeTotal))
                )
            )
            .yAxisTitle("")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Limpiar referencias para evitar leaks
        _binding = null
    }
}