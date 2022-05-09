package com.example.educationalplatform.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CourseLike (
    @PrimaryKey var courseId: Int? = null
)