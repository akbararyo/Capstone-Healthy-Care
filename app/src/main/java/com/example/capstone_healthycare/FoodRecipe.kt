package com.example.capstone_healthycare

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.capstone_healthycare.databinding.ActivityFoodRecipeBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class FoodRecipe : AppCompatActivity() {

    private lateinit var binding: ActivityFoodRecipeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFoodRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.bottomNavView
        val navController = findNavController(R.id.navHostFragment)

        navView.setupWithNavController(navController)

        binding.btnBack.setOnClickListener { finish() }
    }


}