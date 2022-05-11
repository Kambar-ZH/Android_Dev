package com.example.educationalplatform.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CourseLike (
    @PrimaryKey(autoGenerate = true) var id: Int? = null,
    @ColumnInfo(name = "course_id") var courseId: Int? = null,
    @ColumnInfo(name = "user_name") var userName: String? = null
)