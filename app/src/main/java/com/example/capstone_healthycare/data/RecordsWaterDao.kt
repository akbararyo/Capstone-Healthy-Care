package com.example.capstone_healthycare.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.capstone_healthycare.model.RecordsWater

@Dao
interface RecordsWaterDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addBMI(water: RecordsWater)

    @Query("DELETE FROM water_table")
    suspend fun deleteAllWater()

    @Query("UPDATE sqlite_sequence SET seq = 0")
    suspend fun resetSeq()

    @Query("SELECT * FROM water_table ORDER BY id ASC")
    fun readAllDataWater(): LiveData<List<RecordsWater>>
}