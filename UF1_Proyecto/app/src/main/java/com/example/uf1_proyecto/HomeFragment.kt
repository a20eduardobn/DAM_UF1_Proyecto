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

//Implement viewbinding
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Implementando viewbinding
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        //Convertir los colores de colors.xml a strings para poder usarlos en el chart
        //Colores principales de la app
        val colorAccentOrange = "#" + Integer.toHexString(ContextCompat.getColor
            (requireContext(), R.color.accent_color_orange)).substring(2)
        val colorAccentYellow = "#" + Integer.toHexString(ContextCompat.getColor
            (requireContext(), R.color.accent_color_yellow)).substring(2)
        val colorPrimary = "#" + Integer.toHexString(ContextCompat.getColor
            (requireContext(), R.color.primary_color)).substring(2)

        //Colores concretos para el grafico, que se corresponden con los tipos de gastos
        val houseExpenseColor = "#" + Integer.toHexString(ContextCompat.getColor
            (requireContext(), R.color.color_house_expense)).substring(2)
        val shoppingExpenseColor = "#" + Integer.toHexString(ContextCompat.getColor
            (requireContext(), R.color.color_shopping_expense)).substring(2)
        val vehicleExpenseColor = "#" + Integer.toHexString(ContextCompat.getColor
            (requireContext(), R.color.color_vehicle_expense)).substring(2)
        val otherExpenseColor = "#" + Integer.toHexString(ContextCompat.getColor
            (requireContext(), R.color.color_other_expense)).substring(2)


        //Creando el chart junto a su modelo de datos
        val chartModel : AAChartModel = AAChartModel()
            .chartType(AAChartType.Bar)
            .title(getString(R.string.balance_mensual))
            .titleStyle(
                AAStyle()
                .color(colorAccentOrange)
                .fontSize(20f)
                .fontWeight(AAChartFontWeightType.Bold)
            )
            .subtitle(getString(R.string.gastos_ingresos))
            .subtitleStyle(
                AAStyle()
                .color(colorAccentYellow)
                .fontSize(15f)
                .fontWeight(AAChartFontWeightType.Bold)
            )
            .dataLabelsEnabled(true)
            .colorsTheme(arrayOf(houseExpenseColor, shoppingExpenseColor,vehicleExpenseColor,otherExpenseColor))
            .backgroundColor(colorPrimary)
            .categories(arrayOf("Vehicle", "House", "Shopping", "Other"))
            .series(
                arrayOf(
                    AASeriesElement()
                        .name("Temperatura")
                        .data(arrayOf(20, 30, 40, 50))
                )
            )
        binding.mainChartView.aa_drawChartWithChartModel(chartModel)

        // Inflate the layout for this fragment
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Limpiar referencias para evitar leaks
        _binding = null
    }
}