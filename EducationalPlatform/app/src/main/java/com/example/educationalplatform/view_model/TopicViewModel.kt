package com.example.educationalplatform.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.educationalplatform.model.Topic
import com.example.educationalplatform.respository.TopicRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class TopicViewModel(private val repository: TopicRepository): ViewModel() {
    val topicList: MutableLiveData<Response<List<Topic>>> = MutableLiveData()
    val selectedTopic: MutableLiveData<Response<Topic>> = MutableLiveData()

    fun loadTopicList() {
        viewModelScope.launch {
            val response = repository.getTopics()
            topicList.value = response
        }
    }

    fun loadSelectedTopic(topicId: Int) {
        viewModelScope.launch {
            val response = repository.getTopicById(topicId = topicId)
            selectedTopic.value = response
        }
    }

    fun loadTopicListByCourse(courseId: Int) {
        viewModelScope.launch {
            val response = repository.getTopicByCourseId(courseId = courseId)
            topicList.value = response
        }
    }
}