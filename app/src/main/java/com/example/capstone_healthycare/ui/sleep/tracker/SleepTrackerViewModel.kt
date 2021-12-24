package com.example.capstone_healthycare.ui.sleep.tracker

import android.app.Application
import androidx.lifecycle.*
import com.example.capstone_healthycare.data.RecordsSleepDao
import com.example.capstone_healthycare.model.RecordsSleep
import com.example.capstone_healthycare.utils.formatNights
import kotlinx.coroutines.launch

class SleepTrackerViewModel(
    val database: RecordsSleepDao,
    application: Application
) : AndroidViewModel(application) {
    private var _showSnackbarEvent = MutableLiveData<Boolean>()
    val showSnackBarEvent: LiveData<Boolean>
        get() = _showSnackbarEvent

    fun doneShowingSnackbar() {
        _showSnackbarEvent.value = false
    }

    private val _navigateToSleepQuality = MutableLiveData<RecordsSleep?>()
    val navigateToSleepQuality: LiveData<RecordsSleep?>
        get() = _navigateToSleepQuality
    private var tonight = MutableLiveData<RecordsSleep?>()
    private val nights = database.getAllNights()
    val nightsString = Transformations.map(nights) { nights ->
        formatNights(nights, application.resources)
    }

    val startButtonVisible = Transformations.map(tonight) {
        it == null
    }
    val stopButtonVisible = Transformations.map(tonight) {
        it != null
    }
    val clearButtonVisible = Transformations.map(nights) {
        it?.isNotEmpty()
    }

    init {
        initializeTonight()
    }

    private fun initializeTonight() {
        viewModelScope.launch {
            tonight.value = getTonightFromDatabase()
        }
    }

    private suspend fun getTonightFromDatabase(): RecordsSleep? {
        var night = database.getTonight()
        if (night?.endTimeMilli != night?.startTimeMilli) {
            night = null
        }
        return night
    }

    fun onStartTracking() {
        viewModelScope.launch {
            val newNight = RecordsSleep()
            insert(newNight)
            tonight.value = getTonightFromDatabase()
        }
    }

    fun onStopTracking() {
        viewModelScope.launch {
            val oldNight = tonight.value ?: return@launch
            oldNight.endTimeMilli = System.currentTimeMillis()
            update(oldNight)
        }
    }

    fun onClear() {
        viewModelScope.launch {
            clear()
            tonight.value = null
            _showSnackbarEvent.value = true
        }
    }

    fun doneNavigating() {
        _navigateToSleepQuality.value = null
    }

    private suspend fun clear() {
        database.clear()
    }

    private suspend fun update(night: RecordsSleep) {
        database.update(night)
    }

    private suspend fun insert(night: RecordsSleep) {
        database.insert(night)
    }
}