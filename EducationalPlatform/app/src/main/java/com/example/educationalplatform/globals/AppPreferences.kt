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

        fun getString(sharedPreferences: SharedPreferences): String? =
            if (sharedPreferences.contains(name)) sharedPreferences.getString(name, "") else null

        fun setString(sharedPreferences: SharedPreferences, value: String?) =
            value?.let { sharedPreferences.edit { putString(name, value) } } ?: remove(
                sharedPreferences
            )

        fun remove(sharedPreferences: SharedPreferences) = sharedPreferences.edit { remove(name) }
    }
}