package com.example.capstone_healthycare.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.capstone_healthycare.model.RecordsWater

@Database(entities = [RecordsWater::class], version = 1, exportSchema = false)
abstract class WaterDatabase: RoomDatabase(){

    abstract fun recordsWaterDao(): RecordsWaterDao

    companion object{
        @Volatile
        private var INSTANCE: WaterDatabase? = null

        fun getDatabase(context: Context): WaterDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WaterDatabase::class.java,
                    "water_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}