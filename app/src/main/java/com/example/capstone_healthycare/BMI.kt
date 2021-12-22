package com.example.capstone_healthycare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.capstone_healthycare.databinding.ActivityBmiBinding

class BMI : AppCompatActivity() {
    private lateinit var binding: ActivityBmiBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBmiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            addBMI.setOnClickListener {
                val intent = Intent(this@BMI, CalculatorBMI::class.java)
                startActivity(intent)
            }
            btnBack.setOnClickListener { finish() }
        }
    }
}