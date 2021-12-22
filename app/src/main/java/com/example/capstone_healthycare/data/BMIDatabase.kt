package com.example.capstone_healthycare.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.capstone_healthycare.model.RecordsBMI

@Database(entities = [RecordsBMI::class], version = 1, exportSchema = false)
abstract class BMIDatabase: RoomDatabase(){

    abstract fun recordsBMIDao(): RecordsBMIDao

    companion object{
        @Volatile
        private var INSTANCE: BMIDatabase? = null

        fun getDatabase(context: Context): BMIDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BMIDatabase::class.java,
                    "bmi_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}