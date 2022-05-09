package com.example.educationalplatform.data.api.model

import com.google.gson.annotations.SerializedName

class UserForm(
    @SerializedName("username")
    var username: String,
    @SerializedName("email")
    var email: String,
    @SerializedName("password1")
    var password1: String,
    @SerializedName("password2")
    var password2: String,
)