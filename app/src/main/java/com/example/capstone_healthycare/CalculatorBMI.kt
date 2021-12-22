package com.example.capstone_healthycare

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.example.capstone_healthycare.data.BMIViewModel
import com.example.capstone_healthycare.data.RecordsBMI
import com.example.capstone_healthycare.databinding.ActivityCalculatorBmiBinding

class CalculatorBMI : AppCompatActivity() {

    private lateinit var binding: ActivityCalculatorBmiBinding
    private lateinit var bmiViewModel: BMIViewModel


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculatorBmiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bmiViewModel = ViewModelProvider(this).get(BMIViewModel::class.java)
        binding.apply {
            btnBack.setOnClickListener{ finish() }
            btnCancel.setOnClickListener { finish() }
            btnSave.setOnClickListener { insertDataToDatabase() }
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
                if (etWeight.text!!.isEmpty() || etAge.text!!.isEmpty()){
                    Toast.makeText(applicationContext, "Empty field not allowed!", Toast.LENGTH_SHORT).show()
                }
                else{
                    val resultBMI = calculateBMI(seekbarHeight.progress)
                    tvResultBMI.text = resultBMI.toString()
                    classifyingBMI(resultBMI)
                }
            }
        }

    }

    private fun classifyingBMI(resultBMI: Float) {
        binding.apply {
            if (resultBMI < 18.5){
                tvIndicator.text = "UNDERWEIGHT"
                tvIndicator.setTextColor(Color.parseColor("#AED9D2"))
            }
            else if (resultBMI < 24.9){
                tvIndicator.text = "NORMAL"
                tvIndicator.setTextColor(Color.parseColor("#9DE657"))
            }
            else if (resultBMI < 29.9){
                tvIndicator.text = "OVERWEIGHT"
                tvIndicator.setTextColor(Color.parseColor("#EADEAF"))
            }
            else if (resultBMI < 34.9){
                tvIndicator.text = "OBESE I"
                tvIndicator.setTextColor(Color.parseColor("#FFD900"))
            }
            else if (resultBMI < 39.9){
                tvIndicator.text = "OBESE II"
                tvIndicator.setTextColor(Color.parseColor("#F68D32"))
            }
            else if (resultBMI > 40){
                tvIndicator.text = "OBESE III"
                tvIndicator.setTextColor(Color.parseColor("#F17983"))
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

        val recordsBMI = RecordsBMI(0, result)
        bmiViewModel.addBMI(recordsBMI)
        Toast.makeText(this, "Data BMI has successfully added.", Toast.LENGTH_LONG).show()
        finish()
    }

}