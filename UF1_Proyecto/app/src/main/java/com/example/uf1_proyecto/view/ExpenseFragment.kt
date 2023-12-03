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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.uf1_proyecto.MainActivity
import com.example.uf1_proyecto.R
import com.example.uf1_proyecto.databinding.FragmentExpenseBinding
import com.example.uf1_proyecto.databinding.FragmentHomeBinding
import com.example.uf1_proyecto.model.Registro
import com.example.uf1_proyecto.model.RegistrosAdapter
import com.example.uf1_proyecto.model.RegistrosViewModel
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartModel
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartType
import com.github.aachartmodel.aainfographics.aachartcreator.AASeriesElement

class ExpenseFragment : Fragment() {

    private var _binding: FragmentExpenseBinding? = null
    private val binding get() = _binding!!

    private lateinit var registrosViewModel: RegistrosViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentExpenseBinding.inflate(inflater, container, false)
        val view = binding.root
        registrosViewModel = (activity as MainActivity).registrosViewModel

        binding.fabAddExpense.setOnClickListener {
            findNavController().navigate(R.id.action_expenseFragment_to_expenseAdderFragment)
        }

        val adapter = RegistrosAdapter()
        binding.expenseRecyclerView.adapter=adapter

        val layoutManager = LinearLayoutManager(requireContext())
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        layoutManager.reverseLayout = true
        layoutManager.stackFromEnd = true // Esto asegura que las vistas se apilen desde el final
        binding.expenseRecyclerView.layoutManager= layoutManager

        registrosViewModel.todosRegistros.observe(viewLifecycleOwner, Observer { registros ->
            registros?.let {
                // Filtrar solo los registros de las categorías especificadas
                val categoriasFiltradas = it.filter { registro ->
                    registro.category == "Shopping" ||
                            registro.category == "House" ||
                            registro.category == "Vehicle" ||
                            registro.category == "Other"
                }

                // Enviar la lista filtrada al adaptador y al gráfico
                adapter.submitList(categoriasFiltradas)
                binding.expenseChartView.aa_drawChartWithChartModel(createChartModel(categoriasFiltradas))
            }
        })

        // Devolver la vista
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

        // Obtener los datos de la base de datos
        val expenseTotal = registros.sumOf {
            when (it.category) {
                "Shopping", "House", "Vehicle", "Other" -> it.amount
                else -> 0.0
            }
        }

        val shoppingExpense = registros.sumOf { if (it.category == "Shopping") it.amount else 0.0 }
        val houseExpense = registros.sumOf { if (it.category == "House") it.amount else 0.0 }
        val vehicleExpense = registros.sumOf { if (it.category == "Vehicle") it.amount else 0.0 }
        val otherExpense = registros.sumOf { if (it.category == "Other") it.amount else 0.0 }

        // Modelo de datos para el chart
        return AAChartModel()
            .chartType(AAChartType.Column)
            .dataLabelsEnabled(false)
            .legendEnabled(false)
            .axesTextColor("#FFFFFF")
            .colorsTheme(arrayOf(colorAccentPrimary))
            .backgroundColor(colorPrimary)
            .categories(arrayOf(getString(R.string.label_chip_shopping),
                    getString(R.string.label_chip_house),
                    getString(R.string.label_chip_vehicle),
                    getString(R.string.label_chip_other),
                )
            )
            .series(
                arrayOf(
                    AASeriesElement()
                        .name(getString(R.string.totalWord))
                        .data(arrayOf(shoppingExpense,houseExpense,vehicleExpense,otherExpense))
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