package com.example.educationalplatform.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.educationalplatform.data.api.model.Step
import com.example.educationalplatform.respository.StepRepository
import kotlinx.coroutines.launch

class StepViewModel(private val repository: StepRepository) : ViewModel() {
    val errorMessage: MutableLiveData<String> = MutableLiveData()
    val selectedStep: MutableLiveData<Step> = MutableLiveData()

    fun loadSelectedStep(stepId: Int) {
        viewModelScope.launch {
            val response = repository.getStepById(stepId = stepId)
            if (response.isSuccessful) {
                selectedStep.value = response.body()
            } else {
                errorMessage.value = response.message()
            }
        }
    }
}