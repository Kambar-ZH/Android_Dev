package com.example.educationalplatform.data.api

import com.example.educationalplatform.interceptors.AuthInterceptor
import com.example.educationalplatform.data.api.model.AuthToken
import com.example.educationalplatform.data.api.model.Credentials
import com.example.educationalplatform.data.api.model.UserForm
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

fun createAuthService(): AuthService {
    val authInterceptor = AuthInterceptor()

    val client = OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    return retrofit.create(AuthService::class.java)
}

interface AuthService {
    @POST("login/")
    suspend fun login(@Body credentials: Credentials): Response<AuthToken>

    @POST("signup/")
    suspend fun signup(@Body userForm: UserForm): Response<Void>
}