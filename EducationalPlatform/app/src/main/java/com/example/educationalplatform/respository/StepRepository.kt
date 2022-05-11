package com.example.educationalplatform.respository

import com.example.educationalplatform.data.api.StepService
import com.example.educationalplatform.data.api.model.Step
import retrofit2.Response

class StepRepository(private val service: StepService) {
    suspend fun getStepById(stepId: Int): Response<Step> {
        return service.getStepById(stepId = stepId)
    }
}