package com.example.capstone_healthycare.ui.sleep

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.capstone_healthycare.R
import com.example.capstone_healthycare.data.SleepDatabase
import com.example.capstone_healthycare.databinding.ActivitySleepBinding
import com.example.capstone_healthycare.ui.sleep.tracker.SleepTrackerViewModel
import com.example.capstone_healthycare.ui.sleep.tracker.SleepTrackerViewModelFactory
import com.google.android.material.snackbar.Snackbar

class Sleep : AppCompatActivity() {

    private lateinit var binding: ActivitySleepBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySleepBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val dataSource = SleepDatabase.getInstance(application).sleepDatabaseDao
        val viewModelFactory = SleepTrackerViewModelFactory(dataSource, application)
        val sleepTrackerViewModel =
            ViewModelProvider(this, viewModelFactory)[SleepTrackerViewModel::class.java]

        binding.lifecycleOwner = this
        binding.sleepTrackerViewModel = sleepTrackerViewModel


        sleepTrackerViewModel.showSnackBarEvent.observe(this, {
            if (it == true) {
                Snackbar.make(findViewById(android.R.id.content),
                    getString(R.string.cleared_message),
                    Snackbar.LENGTH_SHORT
                ).show()
                sleepTrackerViewModel.doneShowingSnackbar()
            }
        })
    }
}