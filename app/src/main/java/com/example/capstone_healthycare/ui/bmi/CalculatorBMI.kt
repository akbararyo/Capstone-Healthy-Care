package com.example.capstone_healthycare.ui.bmi

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.SeekBar
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.example.capstone_healthycare.viewmodel.BMIViewModel
import com.example.capstone_healthycare.model.RecordsBMI
import com.example.capstone_healthycare.databinding.ActivityCalculatorBmiBinding

class CalculatorBMI : AppCompatActivity() {

    private lateinit var binding: ActivityCalculatorBmiBinding
    private lateinit var bmiViewModel: BMIViewModel


    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculatorBmiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bmiViewModel = ViewModelProvider(this)[BMIViewModel::class.java]
        binding.apply {
            btnBack.setOnClickListener{ finish() }
            btnCancel.setOnClickListener { finish() }
            tvHeight.text = "160"
            seekbarHeight.min = 100
            seekbarHeight.max = 250
            seekbarHeight.progress = 160
            seekbarHeight.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    tvHeight.text = progress.toString()
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {
                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                }

            })

            btncalculateBMI.setOnClickListener {
                if (inputCheck(etWeight.text, etAge.text)){
                    val resultBMI = calculateBMI(seekbarHeight.progress)
                    val roundedBMI = String.format("%.2f", resultBMI)
                    tvResultBMI.text = roundedBMI
                    classifyingBMI(resultBMI)
                }
                else{
                    Toast.makeText(applicationContext, "Empty field not allowed!", Toast.LENGTH_SHORT).show()
                }
            }


            btnSave.setOnClickListener {
                if (inputCheck(etWeight.text, etAge.text) && tvResultBMI.text != "0"){
                    insertDataToDatabase()
                }
                else{
                    Toast.makeText(applicationContext, "Empty field not allowed!", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    private fun inputCheck(etWeight: Editable?, etAge: Editable?): Boolean {
        return !(etWeight!!.isEmpty() && etAge!!.isEmpty())
    }

    @SuppressLint("SetTextI18n")
    private fun classifyingBMI(resultBMI: Float) {
        binding.apply {
            when {
                resultBMI < 18.5 -> {
                    tvIndicator.text = "UNDERWEIGHT"
                    tvIndicator.setTextColor(Color.parseColor("#AED9D2"))
                }
                resultBMI < 24.9 -> {
                    tvIndicator.text = "NORMAL"
                    tvIndicator.setTextColor(Color.parseColor("#9DE657"))
                }
                resultBMI < 29.9 -> {
                    tvIndicator.text = "OVERWEIGHT"
                    tvIndicator.setTextColor(Color.parseColor("#EADEAF"))
                }
                resultBMI < 34.9 -> {
                    tvIndicator.text = "OBESE I"
                    tvIndicator.setTextColor(Color.parseColor("#FFD900"))
                }
                resultBMI < 39.9 -> {
                    tvIndicator.text = "OBESE II"
                    tvIndicator.setTextColor(Color.parseColor("#F68D32"))
                }
                resultBMI > 40 -> {
                    tvIndicator.text = "OBESE III"
                    tvIndicator.setTextColor(Color.parseColor("#F17983"))
                }
            }
        }
    }

    private fun calculateBMI(progress: Int): Float {
        val height = (progress.toFloat() / 100)
        val weight = binding.etWeight.text.toString().toFloat()
        return weight / (height * height)

    }

    private fun insertDataToDatabase() {
        val result = binding.tvResultBMI.text.toString()
        val indicator = binding.tvIndicator.text.toString()

        val recordsBMI = RecordsBMI(0, result, indicator)
        bmiViewModel.addBMI(recordsBMI)
        Toast.makeText(this, "Data BMI has successfully added.", Toast.LENGTH_LONG).show()
        finish()
    }

}