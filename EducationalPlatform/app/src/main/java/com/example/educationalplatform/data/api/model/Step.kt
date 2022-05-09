package com.example.educationalplatform.data.api.model

import com.google.gson.annotations.SerializedName

class Step(
    @SerializedName("id")
    var id: Int,
    @SerializedName("description")
    var description: String,
    @SerializedName("video")
    var video: Video
) {
    override fun equals(other: Any?): Boolean =
        other is Step &&
                other.id == id

    override fun hashCode(): Int {
        return id
    }
}