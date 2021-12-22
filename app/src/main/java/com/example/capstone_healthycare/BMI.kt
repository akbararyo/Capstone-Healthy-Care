package com.example.capstone_healthycare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.capstone_healthycare.viewmodel.BMIViewModel
import com.example.capstone_healthycare.databinding.ActivityBmiBinding
import com.example.capstone_healthycare.listadapter.ListAdapterBMI

class BMI : AppCompatActivity() {
    private lateinit var binding: ActivityBmiBinding
    private lateinit var mBMIViewModel: BMIViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBmiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val listAdapter = ListAdapterBMI()
        val recyclerView = binding.rvBmi
        recyclerView.adapter = listAdapter
        recyclerView.layoutManager = LinearLayoutManager(this@BMI)


        mBMIViewModel = ViewModelProvider(this)[BMIViewModel::class.java]
        mBMIViewModel.readAllData.observe(this, Observer { bmi ->
            listAdapter.setData(bmi)
        })

        binding.apply {
            addBMI.setOnClickListener {
                val intent = Intent(this@BMI, CalculatorBMI::class.java)
                startActivity(intent)
            }
            btnBack.setOnClickListener { finish() }
            btnDelete.setOnClickListener {
                val builder = AlertDialog.Builder(this@BMI)
                builder.setPositiveButton("Yes") {_, _ ->
                    mBMIViewModel.deleteAll()
                    Toast.makeText(this@BMI, "Successfully removed all data", Toast.LENGTH_SHORT).show()
                }
                builder.setNegativeButton("No") {_, _ -> }
                builder.setTitle("Delete all data?")
                builder.create().show()
            }
        }
    }
}