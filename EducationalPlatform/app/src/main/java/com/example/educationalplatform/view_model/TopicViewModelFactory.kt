package com.example.educationalplatform.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.educationalplatform.respository.TopicRepository


class TopicViewModelFactory(private val repository: TopicRepository)
    : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TopicViewModel(repository) as T
    }
}