package com.example.educationalplatform.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.educationalplatform.model.Course
import com.example.educationalplatform.respository.CourseRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class CourseViewModel(private val repository: CourseRepository): ViewModel() {
    val courseList: MutableLiveData<Response<List<Course>>> = MutableLiveData()
    val selectedCourse: MutableLiveData<Response<Course>> = MutableLiveData()

    fun loadCourseList() {
        viewModelScope.launch {
            val response = repository.getCourses()
            courseList.value = response
        }
    }

    fun loadSelectedCourse(courseId: Int) {
        viewModelScope.launch {
            val response = repository.getCourseById(courseId = courseId)
            selectedCourse.value = response
        }
    }
}