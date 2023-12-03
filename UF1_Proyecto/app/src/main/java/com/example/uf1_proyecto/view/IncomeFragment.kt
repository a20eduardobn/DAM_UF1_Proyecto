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
import com.example.uf1_proyecto.databinding.FragmentIncomeBinding
import com.example.uf1_proyecto.model.Registro
import com.example.uf1_proyecto.model.RegistrosAdapter
import com.example.uf1_proyecto.model.RegistrosViewModel
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartModel
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartType
import com.github.aachartmodel.aainfographics.aachartcreator.AASeriesElement
import java.text.SimpleDateFormat
import java.util.Calendar

class IncomeFragment : Fragment() {

    private var _binding: FragmentIncomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var registrosViewModel: RegistrosViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentIncomeBinding.inflate(inflater, container, false)
        val view = binding.root
        registrosViewModel = (activity as MainActivity).registrosViewModel

        binding.fabAddIncome.setOnClickListener {
            findNavController().navigate(R.id.action_incomeFragment_to_incomeAdderFragment)
        }

        val adapter = RegistrosAdapter()
        binding.incomeRecyclerView.adapter = adapter

        val layoutManager = LinearLayoutManager(requireContext())
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        layoutManager.reverseLayout = true
        layoutManager.stackFromEnd = true // Esto asegura que las vistas se apilen desde el final
        binding.incomeRecyclerView.layoutManager = layoutManager

        // Configuramos el observador para el viewmodel, en este caso lo filtramos
        // para solo admitir ingresos
        registrosViewModel.todosRegistros.observe(viewLifecycleOwner, Observer { registros ->
            registros?.let {
                // Filtrar solo los registros de la categoría "Income"
                val registrosIncome = it.filter { registro -> registro.category == "Income" }

                // Enviar la lista filtrada al adaptador y al gráfico
                adapter.submitList(registrosIncome)
                binding.incomeChartView.aa_drawChartWithChartModel(createChartModel(registrosIncome))
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

        // Obtener el último día del mes actual
        val lastDayOfMonth = Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH)

        // Crear un rango de días del 1 al último día del mes
        val daysOfMonth = (1..lastDayOfMonth)

        // Obtener los registros del mes actual
        val currentMonthRecords = registros.filter {
            val recordDate = SimpleDateFormat("dd/MM/yyyy").parse(it.date)
            val currentMonth = Calendar.getInstance().get(Calendar.MONTH)
            val recordMonth = Calendar.getInstance().apply { time = recordDate }.get(Calendar.MONTH)
            currentMonth == recordMonth
        }

// Agrupar los registros por día
        val recordsByDay = currentMonthRecords.groupBy {
            val recordDate = SimpleDateFormat("dd/MM/yyyy").parse(it.date)
            Calendar.getInstance().apply { time = recordDate }.get(Calendar.DAY_OF_MONTH)
        }

// Obtener las categorías (días del mes)
        val categories = daysOfMonth.map { it.toString() }

// Obtener los totales para cada día, si no hay registros, asignar 0.0
        val totalsByDay = daysOfMonth.map { day ->
            recordsByDay[day]?.sumByDouble { it.amount } ?: 0.0
        }

// Modelo de datos para el chart
        return AAChartModel()
            .chartType(AAChartType.Line)
            .dataLabelsEnabled(false)
            .legendEnabled(false)
            .axesTextColor("#FFFFFF")
            .colorsTheme(arrayOf(colorAccentPrimary))
            .backgroundColor(colorPrimary)
            .categories(categories.toTypedArray())
            .series(
                arrayOf(
                    AASeriesElement()
                        .name(getString(R.string.totalWord))
                        .data(totalsByDay.toTypedArray())
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