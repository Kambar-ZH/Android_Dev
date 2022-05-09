package com.example.educationalplatform.data.api.model

import com.google.gson.annotations.SerializedName

class Credentials(
    @SerializedName("password")
    var password: String,
    @SerializedName("username")
    var username: String,
)