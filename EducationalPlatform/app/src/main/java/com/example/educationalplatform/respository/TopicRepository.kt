package com.example.educationalplatform.respository

import com.example.educationalplatform.data.api.TopicService
import com.example.educationalplatform.data.api.model.Topic
import retrofit2.Response

class TopicRepository(private val service: TopicService) {
    suspend fun getTopicByCourseId(courseId: Int): Response<List<Topic>> {
        return service.getTopicsByCourseId(courseId = courseId)
    }
}