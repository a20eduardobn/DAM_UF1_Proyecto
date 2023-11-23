package com.example.uf1_proyecto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.uf1_proyecto.databinding.ActivityMainBinding
import com.example.uf1_proyecto.databinding.FragmentHomeBinding

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Create the view binding object
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //Add the material toolbar
        setSupportActionBar(binding.toolbar)
        //Change the title of the toolbar
        supportActionBar?.title = getString(R.string.balance_mensual)
    }
}