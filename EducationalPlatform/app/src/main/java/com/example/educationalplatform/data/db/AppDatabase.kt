package com.example.educationalplatform.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.educationalplatform.data.db.dao.CourseLikeDao
import com.example.educationalplatform.data.db.model.CourseLike


@Database(
    entities = [CourseLike::class],
    version = 2,
)
abstract class AppDatabase: RoomDatabase() {
    companion object {
        const val DATABASE_NAME = "edu_db"
    }
    abstract fun courseLikeDao(): CourseLikeDao
}
