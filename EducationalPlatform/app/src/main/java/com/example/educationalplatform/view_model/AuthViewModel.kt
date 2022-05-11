package com.example.educationalplatform.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.educationalplatform.data.api.model.AuthToken
import com.example.educationalplatform.data.api.model.Credentials
import com.example.educationalplatform.data.api.model.UserForm
import com.example.educationalplatform.globals.MainApplication
import com.example.educationalplatform.respository.AuthRepository
import kotlinx.coroutines.launch

class AuthViewModel(private val repository: AuthRepository) : ViewModel() {
    val errorMessage = MutableLiveData<String>()
    val loginResponse: MutableLiveData<AuthToken> = MutableLiveData()
    val signupResponse: MutableLiveData<Void> = MutableLiveData()

    fun signup(userForm: UserForm) {
        viewModelScope.launch {
            val response = repository.signup(userForm = userForm)
            if (response.isSuccessful) {
                signupResponse.value = response.body()
            } else {
                errorMessage.value = response.message()
            }
        }
    }

    fun login(credentials: Credentials) {
        viewModelScope.launch {
            val response = repository.login(credentials = credentials)
            if (response.isSuccessful) {
                val authToken = response.body()
                if (authToken != null) {
                    MainApplication.instance.appPreferences.accessToken = authToken.access
                    MainApplication.instance.appPreferences.refreshToken = authToken.refresh
                    MainApplication.instance.appPreferences.userName = credentials.username
                }
                loginResponse.value = response.body()
            } else {
                errorMessage.value = response.message()
            }
        }
    }
}