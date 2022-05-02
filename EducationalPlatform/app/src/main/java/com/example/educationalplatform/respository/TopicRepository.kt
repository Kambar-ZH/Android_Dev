package com.example.educationalplatform.respository

import com.example.educationalplatform.api.TopicService
import com.example.educationalplatform.model.Topic
import retrofit2.Response

class TopicRepository(private val service: TopicService) {
    suspend fun getTopics(): Response<List<Topic>> {
        return service.getTopics()
    }

    suspend fun getTopicById(topicId: Int): Response<Topic> {
        return service.getTopicById(topicId = topicId)
    }

    suspend fun getTopicByCourseId(courseId: Int): Response<List<Topic> > {
        return service.getTopicsByCourseId(courseId = courseId)
    }
}