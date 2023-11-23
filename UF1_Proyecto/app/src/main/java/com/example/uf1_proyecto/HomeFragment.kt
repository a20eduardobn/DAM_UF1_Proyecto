package com.example.uf1_proyecto

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.uf1_proyecto.databinding.FragmentHomeBinding
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartFontWeightType
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartModel
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartType
import com.github.aachartmodel.aainfographics.aachartcreator.AASeriesElement
import com.github.aachartmodel.aainfographics.aaoptionsmodel.AAStyle

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        //Creando el chart a partir del modelo de datos
        binding.mainChartView.aa_drawChartWithChartModel(createChartModel())

        // Devolver la vista
        return view
    }

    //Funcion que crea el modelo de datos para el chart
    private fun createChartModel(): AAChartModel {
        //Convertir los colores de colors.xml a strings para poder usarlos en el chart
        val colorPrimary = "#" + Integer.toHexString(
            ContextCompat.getColor
                (requireContext(), R.color.colorPrimary)
        ).substring(2)
        val colorAccentPrimary = "#" + Integer.toHexString(
            ContextCompat.getColor
                (requireContext(), R.color.accent_color_primary)
        ).substring(2)

        //Modelo de datos para el chart
        return AAChartModel()
            .chartType(AAChartType.Column)
            .dataLabelsEnabled(false)
            .legendEnabled(false)
            .axesTextColor("#FFFFFF")
            .colorsTheme(arrayOf(colorAccentPrimary))
            .backgroundColor(colorPrimary)
            .categories(arrayOf(getString(R.string.gastosWord), getString(R.string.ingresosWord)))
            .series(
                arrayOf(
                    AASeriesElement()
                        .name("Temperatura")
                        .data(arrayOf(420, 140))
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