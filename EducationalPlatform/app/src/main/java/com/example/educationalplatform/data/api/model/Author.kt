package com.example.educationalplatform.data.api.model

import com.google.gson.annotations.SerializedName

class Author (
    @SerializedName("first_name")
    var firstName: String,
    @SerializedName("last_name")
    var lastName: String,
    @SerializedName("email")
    var email: String,
)