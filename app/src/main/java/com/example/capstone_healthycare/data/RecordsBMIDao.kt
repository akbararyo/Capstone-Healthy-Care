package com.example.capstone_healthycare.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.capstone_healthycare.model.RecordsBMI

@Dao
interface RecordsBMIDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addBMI(bmi: RecordsBMI)

    @Query("DELETE FROM bmi_table")
    suspend fun deleteAllBMI()

    @Query("UPDATE sqlite_sequence SET seq = 0")
    suspend fun resetSeq()

    @Query("SELECT * FROM bmi_table ORDER BY id ASC")
    fun readAllDataBMI(): LiveData<List<RecordsBMI>>
}