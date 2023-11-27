package com.example.uf1_proyecto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.uf1_proyecto.Controller.Controller
import com.example.uf1_proyecto.databinding.ActivityMainBinding
import com.example.uf1_proyecto.databinding.FragmentHomeBinding

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //Se añade el toolbar de la parte superior
        setSupportActionBar(binding.toolbar)

        val navhostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navhostFragment.navController

        val builderApp = AppBarConfiguration.Builder(navController.graph)
        val configurationApp = builderApp.build()

        binding.toolbar.setupWithNavController(navController, configurationApp)
        //Se actualiza el titulo del toolbar para evitar que se muestre el nombre de la aplicacion
        supportActionBar?.title = getString(R.string.monthly_balance_text)
    }
}