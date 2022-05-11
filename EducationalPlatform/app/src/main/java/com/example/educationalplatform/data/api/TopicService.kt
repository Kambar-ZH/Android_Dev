package com.example.educationalplatform.data.api

import com.example.educationalplatform.interceptors.AuthInterceptor
import com.example.educationalplatform.data.api.model.Topic
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

fun createTopicService(): TopicService {
    val authInterceptor = AuthInterceptor()

    val client = OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    return retrofit.create(TopicService::class.java)
}

interface TopicService {
    @GET("courses/{course_id}/topics/")
    suspend fun getTopicsByCourseId(@Path("course_id") courseId: Int): Response<List<Topic>>
}