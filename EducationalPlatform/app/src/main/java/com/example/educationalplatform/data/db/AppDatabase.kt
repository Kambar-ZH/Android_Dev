package com.example.educationalplatform.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.educationalplatform.data.db.dao.CourseLikeDao
import com.example.educationalplatform.data.db.model.CourseLike


@Database(
    entities = [CourseLike::class],
    version = 2,
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun courseLikeDao(): CourseLikeDao
}
