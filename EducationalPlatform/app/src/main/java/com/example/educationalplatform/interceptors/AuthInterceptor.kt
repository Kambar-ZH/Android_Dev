package com.example.educationalplatform.interceptors

import com.example.educationalplatform.globals.MainApplication
import okhttp3.Interceptor
import okhttp3.Response

private const val HEADER_AUTHORIZATION = "Authorization"
private const val BEARER = "Bearer"

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        val token = MainApplication.instance.appPreferences.accessToken
        builder.header(HEADER_AUTHORIZATION, "$BEARER $token")
        val request = builder.build()
        return chain.proceed(request)
    }
}