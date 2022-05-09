package com.example.educationalplatform.respository

import com.example.educationalplatform.data.api.CourseService
import com.example.educationalplatform.data.api.model.Course
import retrofit2.Response

class CourseRepository(private val service: CourseService) {
    suspend fun getCourses(): Response<List<Course>> {
        return service.getCourses()
    }

    suspend fun getCourseById(courseId: Int): Response<Course> {
        return service.getCourseById(courseId = courseId)
    }

    suspend fun likeCourse(courseId: Int): Response<Void> {
        return service.likeCourse(courseId = courseId)
    }
}