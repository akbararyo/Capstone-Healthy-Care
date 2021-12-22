package com.example.capstone_healthycare.data

import androidx.lifecycle.LiveData

class BMIRepository(private val BMIDao: RecordsBMIDao) {

    val readAllData: LiveData<List<RecordsBMI>> = BMIDao.readAllData()

    suspend fun addBMI(bmi: RecordsBMI){
        BMIDao.addBMI(bmi)
    }
}