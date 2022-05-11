package com.example.educationalplatform.globals

import android.app.Application
import androidx.room.Room
import com.example.educationalplatform.data.db.AppDatabase
import com.example.educationalplatform.di.*
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {
    private lateinit var database: AppDatabase
    val appPreferences: AppPreferences by inject()

    override fun onCreate() {
        super.onCreate()
        instance = this
        database = Room.databaseBuilder(this, AppDatabase::class.java, AppDatabase.DATABASE_NAME)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()

        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(applicationModule, viewModelModule, repositoryModule, networkModule)
        }
    }

    fun getDatabase(): AppDatabase {
        return database
    }

    companion object {
        lateinit var instance: MainApplication
    }
}