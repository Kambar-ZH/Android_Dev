package com.example.educationalplatform.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.educationalplatform.respository.CourseRepository
import com.example.educationalplatform.respository.TopicRepository


class TopicViewModelFactory(
    private val topicRepository: TopicRepository,
    private val courseRepository: CourseRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TopicViewModel(
            topicRepository = topicRepository,
            courseRepository = courseRepository
        ) as T
    }
}