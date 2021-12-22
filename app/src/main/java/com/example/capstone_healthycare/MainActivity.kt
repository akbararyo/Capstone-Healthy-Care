package com.example.capstone_healthycare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.capstone_healthycare.databinding.ActivityMainBinding
import java.util.ArrayList

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
        }
    }

}