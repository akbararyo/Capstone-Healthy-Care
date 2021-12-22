package com.example.capstone_healthycare.bmi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.capstone_healthycare.databinding.ActivityUpdateBmiBinding

class UpdateBMI : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateBmiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBmiBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}