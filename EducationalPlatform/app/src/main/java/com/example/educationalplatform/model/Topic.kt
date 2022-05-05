package com.example.educationalplatform.model

import com.google.gson.annotations.SerializedName

class Topic (
    @SerializedName("id")
    var id: Int,
    @SerializedName("title")
    var title: String,
    @SerializedName("steps")
    var steps: List<Step>,
)