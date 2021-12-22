package com.example.capstone_healthycare.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bmi_table")
data class RecordsBMI(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val BMI: String
)
