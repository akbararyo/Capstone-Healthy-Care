package com.example.capstone_healthycare.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.capstone_healthycare.data.WaterDatabase
import com.example.capstone_healthycare.model.RecordsWater
import com.example.capstone_healthycare.repository.WaterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WaterViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<RecordsWater>>
    private val repository: WaterRepository

    init {
        val waterDao = WaterDatabase.getDatabase(application).recordsWaterDao()
        repository = WaterRepository(waterDao)
        readAllData = repository.readAllData
    }

    fun addWater(water: RecordsWater){
        viewModelScope.launch(Dispatchers.IO){
            repository.addWater(water)
        }
    }

    fun deleteAll(){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAll()
            repository.resetSeq()
        }
    }
}