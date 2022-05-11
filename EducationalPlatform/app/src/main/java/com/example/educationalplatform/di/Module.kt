package com.example.educationalplatform.di

import com.example.educationalplatform.data.api.*
import com.example.educationalplatform.globals.AppPreferences
import com.example.educationalplatform.respository.AuthRepository
import com.example.educationalplatform.respository.CourseRepository
import com.example.educationalplatform.respository.StepRepository
import com.example.educationalplatform.respository.TopicRepository
import com.example.educationalplatform.view_model.AuthViewModel
import com.example.educationalplatform.view_model.CourseViewModel
import com.example.educationalplatform.view_model.StepViewModel
import com.example.educationalplatform.view_model.TopicViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val applicationModule = module {
    single { AppPreferences(context = androidContext()) }
}

val viewModelModule = module {
    viewModel { AuthViewModel(repository = get()) }
    viewModel { CourseViewModel(repository = get()) }
    viewModel { TopicViewModel(repository = get()) }
    viewModel { StepViewModel(repository = get()) }
}

val repositoryModule = module {
    single { AuthRepository(service = get()) }
    single { CourseRepository(service = get()) }
    single { TopicRepository(service = get()) }
    single { StepRepository(service = get()) }
}

val networkModule = module {
    single { createAuthService() }
    single { createTopicService() }
    single { createCourseService() }
    single { createStepService() }
}