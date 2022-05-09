package com.example.educationalplatform.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.educationalplatform.R
import com.example.educationalplatform.databinding.ActivityMainBinding
import com.example.educationalplatform.globals.AppPreferences
import com.example.educationalplatform.globals.MainApplication

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AppPreferences.setup(applicationContext)
    }
}