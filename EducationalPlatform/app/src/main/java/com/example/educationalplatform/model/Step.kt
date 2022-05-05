package com.example.educationalplatform.model

import com.google.gson.annotations.SerializedName

class Step (
    @SerializedName("id")
    var id: Int,
    @SerializedName("description")
    var description: String
)