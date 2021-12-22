package com.example.capstone_healthycare.repository

import androidx.lifecycle.LiveData
import com.example.capstone_healthycare.data.RecordsBMIDao
import com.example.capstone_healthycare.model.RecordsBMI
import com.example.capstone_healthycare.model.RecordsWater

class WaterRepository(private val BMIDao: RecordsBMIDao) {

    val readAllData: LiveData<List<RecordsWater>> = BMIDao.readAllDataWater()

    suspend fun addWater(water: RecordsWater){
        BMIDao.addWater(water)
    }

    suspend fun deleteAll(){
        BMIDao.deleteAllWater()
    }

    suspend fun resetSeq(){
        BMIDao.resetSeq()
    }
}