package com.example.capstone_healthycare.ui.bmi

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.capstone_healthycare.data.BMIDatabase
import com.example.capstone_healthycare.model.RecordsBMI
import com.example.capstone_healthycare.repository.BMIRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BMIViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<RecordsBMI>>
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

    fun deleteAll(){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAll()
            repository.resetSeq()
        }
    }
}