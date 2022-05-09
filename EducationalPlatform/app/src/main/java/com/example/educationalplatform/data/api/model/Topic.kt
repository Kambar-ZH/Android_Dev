package com.example.educationalplatform.data.api.model

import com.google.gson.annotations.SerializedName

class Topic(
    @SerializedName("id")
    var id: Int,
    @SerializedName("title")
    var title: String,
    @SerializedName("steps")
    var steps: List<Step>,
) {
    override fun equals(other: Any?): Boolean =
        other is Topic &&
                other.id == id

    override fun hashCode(): Int {
        return id
    }
}