package com.example.educationalplatform.data.api

import com.example.educationalplatform.interceptors.AuthInterceptor
import com.example.educationalplatform.data.api.model.Step
import com.example.educationalplatform.data.db.AppDatabase
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
    suspend fun getSteps(): Response<List<Step>>

    @GET("steps/{step_id}/")
    suspend fun getStepById(@Path("step_id") stepId: Int): Response<Step>
}