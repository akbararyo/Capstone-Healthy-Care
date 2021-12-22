package com.example.capstone_healthycare.repository

import androidx.lifecycle.LiveData
import com.example.capstone_healthycare.data.RecordsBMIDao
import com.example.capstone_healthycare.model.RecordsBMI

class BMIRepository(private val BMIDao: RecordsBMIDao) {

    val readAllData: LiveData<List<RecordsBMI>> = BMIDao.readAllDataBMI()

    suspend fun addBMI(bmi: RecordsBMI){
        BMIDao.addBMI(bmi)
    }

    suspend fun deleteAll(){
        BMIDao.deleteAllBMI()
    }

    suspend fun resetSeq(){
        BMIDao.resetSeq()
    }
}