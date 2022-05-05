package com.example.educationalplatform.model

import com.google.gson.annotations.SerializedName

class Credentials (
    @SerializedName("password")
    var password: String,
    @SerializedName("username")
    var username: String,
)