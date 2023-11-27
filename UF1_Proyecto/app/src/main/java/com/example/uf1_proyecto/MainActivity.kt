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
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        when (item.itemId) {
            // Item del menu, que se muestra en el fragmento de añadir gastos
            // y lanza la funcion de añadir gastos
            R.id.menu_item_add -> {
                launchFormSubmission()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
        return NavigationUI.onNavDestinationSelected(item, navController)
                || super.onOptionsItemSelected(item)
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        // Hide or show menu items based on the currently displayed fragment
        val isExpenseAdderFragment: Boolean = findNavController(R.id.nav_host_fragment).currentDestination?.id == R.id.expenseAdderFragment
        menu.findItem(R.id.menu_item_add).isVisible = isExpenseAdderFragment
        return super.onPrepareOptionsMenu(menu)
    }
}