package com.example.educationalplatform.view_model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.educationalplatform.data.api.model.Course
import com.example.educationalplatform.data.db.model.CourseLike
import com.example.educationalplatform.globals.MainApplication
import com.example.educationalplatform.respository.CourseRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class CourseViewModel(private val repository: CourseRepository) : ViewModel() {
    val errorMessage: MutableLiveData<String> = MutableLiveData()
    val courseList: MutableLiveData<List<Course>> = MutableLiveData()
    val selectedCourse: MutableLiveData<Course> = MutableLiveData()
    val likedCourse: MutableLiveData<Void> = MutableLiveData()

    fun loadCourseList() {
        viewModelScope.launch {
            val response = repository.getCourses()
            if (response.isSuccessful) {
                val courseListResponse = response.body()
                val likedCourses = MainApplication.instance.getDatabase()!!.courseLikeDao().getAll()
                if (courseListResponse != null) {
                    for (i in courseListResponse.indices) {
                        for (j in likedCourses.indices) {
                            if (likedCourses[j].courseId == courseListResponse[i].id &&
                                likedCourses[j].userName == MainApplication.instance.appPreferences.userName
                            ) {
                                courseListResponse[i].isLiked = true
                            }
                        }
                    }
                }
                courseList.value = courseListResponse
            } else {
                errorMessage.value = response.message()
            }
        }
    }

    fun loadSelectedCourse(courseId: Int) {
        viewModelScope.launch {
            val response = repository.getCourseById(courseId = courseId)
            if (response.isSuccessful) {
                selectedCourse.value = response.body()
            } else {
                errorMessage.value = response.message()
            }
        }
    }

    fun likeCourse(courseId: Int) {
        viewModelScope.launch {
            val response = repository.likeCourse(courseId = courseId)
            if (response.isSuccessful) {
                likedCourse.value = response.body()
                MainApplication.instance.getDatabase()!!.courseLikeDao()
                    .insert(
                        CourseLike(
                            courseId = courseId,
                            userName = MainApplication.instance.appPreferences.userName!!
                        )
                    )
            } else {
                errorMessage.value = response.message()
            }
        }
    }
}