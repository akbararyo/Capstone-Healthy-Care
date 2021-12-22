package com.example.capstone_healthycare.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "water_table")
data class RecordsWater(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val water: Int,
    val date: String
)