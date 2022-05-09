package com.example.educationalplatform.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.educationalplatform.data.db.model.CourseLike

@Dao
interface CourseLikeDao {
    @Query("SELECT * FROM courselike")
    fun getAll(): List<CourseLike>

    @Insert()
    fun insert(courseLike: CourseLike): Long

    @Query("DELETE FROM courselike WHERE courseId = :courseId")
    fun deleteByCourseId(courseId: Long)
}