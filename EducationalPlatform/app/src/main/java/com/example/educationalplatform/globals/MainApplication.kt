package com.example.educationalplatform.globals

import android.app.Application
import androidx.room.Room
import com.example.educationalplatform.data.db.AppDatabase

class MainApplication : Application() {
    private lateinit var database: AppDatabase

    override fun onCreate() {
        super.onCreate()
        instance = this
        database = Room.databaseBuilder(this, AppDatabase::class.java, "edu_db")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    fun getDatabase(): AppDatabase? {
        return database
    }

    companion object {
        lateinit var instance: MainApplication
    }
}