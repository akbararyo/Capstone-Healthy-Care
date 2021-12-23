package com.example.capstone_healthycare.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Entity(tableName = "water_table")
data class RecordsWater(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val water: Int = 0,
    val date: String = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE)
)