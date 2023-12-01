package com.example.uf1_proyecto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.uf1_proyecto.databinding.ActivityMainBinding
import com.example.uf1_proyecto.model.RegistrosApplication
import com.example.uf1_proyecto.model.RegistrosViewModel
import com.example.uf1_proyecto.model.RegistrosViewModelFactory

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var registrosViewModel: RegistrosViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        registrosViewModel = ViewModelProvider(this, RegistrosViewModelFactory((application as RegistrosApplication).repository))
            .get(RegistrosViewModel::class.java)

        //Se añade el toolbar de la parte superior
        setSupportActionBar(binding.toolbar)

        val navhostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navhostFragment.navController

        val builderApp = AppBarConfiguration.Builder(navController.graph)
        val configurationApp = builderApp.build()

        binding.toolbar.setupWithNavController(navController, configurationApp)

        //Se actualiza el titulo del toolbar para evitar que se muestre el nombre de la aplicacion
        supportActionBar?.title = getString(R.string.monthly_balance_text)

        binding.bottomNavigation.setupWithNavController(navController)
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.item_expense -> {
                    navController.navigate(R.id.expenseFragment)
                    true
                }

                R.id.item_income -> {
                    navController.navigate(R.id.incomeFragment)
                    true
                }

                R.id.item_balance -> {
                    navController.navigate(R.id.homeFragment)
                    true
                }
                else -> false
            }
        }



    }
}