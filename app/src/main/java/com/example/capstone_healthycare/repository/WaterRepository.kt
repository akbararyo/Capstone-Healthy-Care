package com.example.capstone_healthycare.repository

import androidx.lifecycle.LiveData
import com.example.capstone_healthycare.data.RecordsWaterDao
import com.example.capstone_healthycare.model.RecordsWater

class WaterRepository(private val WaterDao: RecordsWaterDao) {

    val readAllData: LiveData<List<RecordsWater>> = WaterDao.readAllDataWater()

    suspend fun addWater(bmi: RecordsWater){
        WaterDao.addBMI(bmi)
    }

    suspend fun deleteAll(){
        WaterDao.deleteAllWater()
    }

    suspend fun resetSeq(){
        WaterDao.resetSeq()
    }
}