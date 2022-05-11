package com.example.educationalplatform.data.api

import com.example.educationalplatform.interceptors.AuthInterceptor
import com.example.educationalplatform.data.api.model.Course
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

const val url = "http://10.0.2.2:8000/api/v1/"

fun createCourseService(): CourseService {
    val authInterceptor = AuthInterceptor()

    val client = OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    return retrofit.create(CourseService::class.java)
}

interface CourseService {
    @GET("courses/")
    suspend fun getCourses(): Response<List<Course>>

    @GET("courses/{course_id}/")
    suspend fun getCourseById(@Path("course_id") courseId: Int): Response<Course>

    @POST("courses/{course_id}/like/")
    suspend fun likeCourse(@Path("course_id") courseId: Int): Response<Void>
}