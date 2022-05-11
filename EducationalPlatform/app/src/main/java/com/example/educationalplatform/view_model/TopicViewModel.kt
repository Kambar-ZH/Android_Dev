package com.example.educationalplatform.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.educationalplatform.data.api.model.Topic
import com.example.educationalplatform.respository.TopicRepository
import kotlinx.coroutines.launch

class TopicViewModel(
    private val repository: TopicRepository,
) : ViewModel() {
    val errorMessage: MutableLiveData<String> = MutableLiveData()
    val topicList: MutableLiveData<List<Topic>> = MutableLiveData()

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