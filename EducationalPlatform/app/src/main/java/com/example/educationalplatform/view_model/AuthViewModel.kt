package com.example.educationalplatform.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.educationalplatform.model.AuthToken
import com.example.educationalplatform.model.Credentials
import com.example.educationalplatform.model.UserForm
import com.example.educationalplatform.respository.AuthRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class AuthViewModel(private val repository: AuthRepository): ViewModel() {
    val loginResponse: MutableLiveData<Response<AuthToken>> = MutableLiveData()
    val signupResponse: MutableLiveData<Response<Void>> = MutableLiveData()

    fun signup(userForm: UserForm) {
        viewModelScope.launch {
            val response = repository.signup(userForm=userForm)
            signupResponse.value = response
        }
    }

    fun login(credentials: Credentials) {
        viewModelScope.launch {
            val response = repository.login(credentials=credentials)
            loginResponse.value = response
        }
    }
}