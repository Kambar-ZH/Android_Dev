package com.example.educationalplatform.api

import com.example.educationalplatform.interceptors.AuthInterceptor
import com.example.educationalplatform.model.Course
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

fun createStepService(): StepService {
    val authInterceptor = AuthInterceptor()
    val client = OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .build()
    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    return retrofit.create(StepService::class.java)
}

interface StepService {
    @GET("steps/")
    suspend fun getCourses(): Response<List<Course>>

    @GET("steps/{id}/")
    suspend fun getCourseById(@Path("id") courseId: Int): Response<Course>
}