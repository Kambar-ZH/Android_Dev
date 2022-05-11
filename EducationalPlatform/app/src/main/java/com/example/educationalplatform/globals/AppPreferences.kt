package com.example.educationalplatform.globals

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.core.content.edit

class AppPreferences(context: Context) {

    private var sharedPreferences =
        context.getSharedPreferences("Educational Platform.sharedprefs", MODE_PRIVATE)

    var accessToken: String?
        get() = Key.ACCESS_TOKEN.getString(sharedPreferences)
        set(value) = Key.ACCESS_TOKEN.setString(sharedPreferences, value)

    var refreshToken: String?
        get() = Key.REFRESH_TOKEN.getString(sharedPreferences)
        set(value) = Key.REFRESH_TOKEN.setString(sharedPreferences, value)

    var userName: String?
        get() = Key.USER_NAME.getString(sharedPreferences)
        set(value) = Key.USER_NAME.setString(sharedPreferences, value)

    private enum class Key {
        ACCESS_TOKEN, REFRESH_TOKEN, USER_NAME;

        fun getBoolean(sharedPreferences: SharedPreferences): Boolean? =
            if (sharedPreferences.contains(name)) sharedPreferences.getBoolean(
                name,
                false
            ) else null

        fun getFloat(sharedPreferences: SharedPreferences): Float? =
            if (sharedPreferences.contains(name)) sharedPreferences.getFloat(name, 0f) else null

        fun getInt(sharedPreferences: SharedPreferences): Int? =
            if (sharedPreferences.contains(name)) sharedPreferences.getInt(name, 0) else null

        fun getLong(sharedPreferences: SharedPreferences): Long? =
            if (sharedPreferences.contains(name)) sharedPreferences.getLong(name, 0) else null

        fun getString(sharedPreferences: SharedPreferences): String? =
            if (sharedPreferences.contains(name)) sharedPreferences.getString(name, "") else null

        fun setBoolean(sharedPreferences: SharedPreferences, value: Boolean?) =
            value?.let { sharedPreferences.edit { putBoolean(name, value) } } ?: remove(
                sharedPreferences
            )

        fun setFloat(sharedPreferences: SharedPreferences, value: Float?) =
            value?.let { sharedPreferences.edit { putFloat(name, value) } } ?: remove(
                sharedPreferences
            )

        fun setInt(sharedPreferences: SharedPreferences, value: Int?) =
            value?.let { sharedPreferences.edit { putInt(name, value) } } ?: remove(
                sharedPreferences
            )

        fun setLong(sharedPreferences: SharedPreferences, value: Long?) =
            value?.let { sharedPreferences.edit { putLong(name, value) } } ?: remove(
                sharedPreferences
            )

        fun setString(sharedPreferences: SharedPreferences, value: String?) =
            value?.let { sharedPreferences.edit { putString(name, value) } } ?: remove(
                sharedPreferences
            )

        fun exists(sharedPreferences: SharedPreferences): Boolean = sharedPreferences.contains(name)
        fun remove(sharedPreferences: SharedPreferences) = sharedPreferences.edit { remove(name) }
    }
}