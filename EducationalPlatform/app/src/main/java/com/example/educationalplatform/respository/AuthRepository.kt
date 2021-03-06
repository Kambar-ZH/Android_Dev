package com.example.educationalplatform.respository

import com.example.educationalplatform.data.api.AuthService
import com.example.educationalplatform.data.api.model.*
import retrofit2.Response

class AuthRepository(private val service: AuthService) {
    suspend fun signup(userForm: UserForm): Response<Void> {
        return service.signup(userForm = userForm)
    }

    suspend fun login(credentials: Credentials): Response<AuthToken> {
        return service.login(credentials = credentials)
    }
}