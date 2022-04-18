package com.example.educationalplatform.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.educationalplatform.respository.CourseRepository


class CourseViewModelFactory(private val repository: CourseRepository)
    : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CourseViewModel(repository) as T
    }
}