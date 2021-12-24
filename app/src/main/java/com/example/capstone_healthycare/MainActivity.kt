package com.example.capstone_healthycare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.cardview.widget.CardView
import com.example.capstone_healthycare.databinding.ActivityMainBinding
import com.example.capstone_healthycare.ui.bmi.BMI
import com.example.capstone_healthycare.ui.water.Water

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val moveFoodActivity: CardView = binding.cvFood
        moveFoodActivity.setOnClickListener(this)

        val moveBMIActivity: CardView = binding.cvBMI
        moveBMIActivity.setOnClickListener(this)

        val moveWaterActivity: CardView = binding.cvWater
        moveWaterActivity.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.cvFood -> {
                val intent = Intent(this@MainActivity, FoodRecipe::class.java)
                startActivity(intent)
            }
            R.id.cvBMI -> {
                val intent = Intent(this@MainActivity, BMI::class.java)
                startActivity(intent)
            }
            R.id.cvWater -> {
                val intent = Intent(this@MainActivity, Water::class.java)
                startActivity(intent)
            }
        }
    }

}