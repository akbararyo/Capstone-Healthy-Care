package com.example.capstone_healthycare

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.capstone_healthycare.databinding.ActivityWaterBinding

class Water : AppCompatActivity() {

    private lateinit var binding: ActivityWaterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWaterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnBack.setOnClickListener { finish() }
        }
    }
}