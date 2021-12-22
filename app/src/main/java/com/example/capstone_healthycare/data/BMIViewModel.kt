package com.example.capstone_healthycare.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BMIViewModel(application: Application): AndroidViewModel(application) {

    private val readAllData: LiveData<List<RecordsBMI>>
    private val repository: BMIRepository

    init {
        val bmiDao = BMIDatabase.getDatabase(application).recordsBMIDao()
        repository = BMIRepository(bmiDao)
        readAllData = repository.readAllData
    }

    fun addBMI(bmi: RecordsBMI){
        viewModelScope.launch(Dispatchers.IO){
            repository.addBMI(bmi)
        }
    }
}