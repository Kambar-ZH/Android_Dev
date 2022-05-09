package com.example.educationalplatform.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.educationalplatform.respository.StepRepository

class StepViewModelFactory(private val repository: StepRepository)
    : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return StepViewModel(repository) as T
    }
}