package com.example.educationalplatform.respository

import com.example.educationalplatform.data.api.AuthService
import com.example.educationalplatform.data.api.model.*
import com.example.educationalplatform.globals.AppPreferences
import retrofit2.Response

class AuthRepository(private val service: AuthService) {
    suspend fun signup(userForm: UserForm): Response<Void> {
        return service.signup(userForm=userForm)
    }

    suspend fun login(credentials: Credentials): Response<AuthToken> {
        var response = service.login(credentials=credentials)
        if (response.isSuccessful) {
            val authToken = response.body()
            if (authToken != null) {
                AppPreferences.accessToken = authToken.access
                AppPreferences.refreshToken = authToken.refresh
            }
        }
        return response
    }
}