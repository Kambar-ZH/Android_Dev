package com.example.educationalplatform.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.educationalplatform.data.api.model.Topic
import com.example.educationalplatform.data.db.model.CourseLike
import com.example.educationalplatform.globals.MainApplication
import com.example.educationalplatform.respository.CourseRepository
import com.example.educationalplatform.respository.TopicRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class TopicViewModel(
    private val repository: TopicRepository,
) : ViewModel() {
    val errorMessage: MutableLiveData<String> = MutableLiveData()
    val topicList: MutableLiveData<List<Topic>> = MutableLiveData()
    val selectedTopic: MutableLiveData<Topic> = MutableLiveData()

    fun loadTopicList() {
        viewModelScope.launch {
            val response = repository.getTopics()
            if (response.isSuccessful) {
                topicList.value = response.body()
            } else {
                errorMessage.value = response.message()
            }
        }
    }

    fun loadSelectedTopic(topicId: Int) {
        viewModelScope.launch {
            val response = repository.getTopicById(topicId = topicId)
            if (response.isSuccessful) {
                selectedTopic.value = response.body()
            } else {
                errorMessage.value = response.message()
            }
        }
    }

    fun loadTopicListByCourse(courseId: Int) {
        viewModelScope.launch {
            val response = repository.getTopicByCourseId(courseId = courseId)
            if (response.isSuccessful) {
                topicList.value = response.body()
            } else {
                errorMessage.value = response.message()
            }
        }
    }
}