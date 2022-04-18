package com.example.educationalplatform.api

import android.content.Context
import androidx.preference.PreferenceManager
import com.example.educationalplatform.interceptors.AuthInterceptor
import com.example.educationalplatform.model.Course
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
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

    @GET("courses/{id}/")
    suspend fun getCourseById(@Path("id") courseId: Int): Response<Course>
}