package com.example.capstone_healthycare.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RecordsBMIDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addBMI(bmi: RecordsBMI)

    @Query("SELECT * FROM bmi_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<RecordsBMI>>
}