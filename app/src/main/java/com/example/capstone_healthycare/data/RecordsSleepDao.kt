package com.example.capstone_healthycare.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.capstone_healthycare.model.RecordsSleep

@Dao
interface RecordsSleepDao {
    @Insert
    suspend fun insert(night: RecordsSleep)

    @Update
    suspend fun update(night: RecordsSleep)

    @Query("SELECT * FROM daily_sleep_table WHERE nightId = :nightId")
    suspend fun get(nightId: Long): RecordsSleep?

    @Query("DELETE FROM daily_sleep_table")
    suspend fun clear()

    @Query("SELECT * FROM daily_sleep_table ORDER BY nightId DESC LIMIT 1")
    suspend fun getTonight(): RecordsSleep?

    @Query("SELECT * FROM daily_sleep_table ORDER BY nightId DESC")
    fun getAllNights(): LiveData<List<RecordsSleep>>
}