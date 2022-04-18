package com.example.educationalplatform.interceptors

import android.content.SharedPreferences
import com.example.educationalplatform.globals.AppPreferences
import okhttp3.Interceptor
import okhttp3.Response

private const val HEADER_AUTHORIZATION = "Authorization"

class AuthInterceptor(): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        val token = AppPreferences.accessToken
        builder.header(HEADER_AUTHORIZATION, "Bearer $token")
        val request = builder.build()
        return chain.proceed(request)
    }
}