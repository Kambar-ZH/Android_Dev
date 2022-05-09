package com.example.educationalplatform.data.api.model

import com.google.gson.annotations.SerializedName

class Course(
    @SerializedName("id")
    var id: Int,
    @SerializedName("title")
    var title: String,
    @SerializedName("description")
    var description: String,
    @SerializedName("created")
    var created: String,
    @SerializedName("category")
    var category: String,
    @SerializedName("publisher_name")
    var publisherName: String,
    @SerializedName("likes_count")
    var likesCount: Int,

    var isLiked: Boolean
) {
    override fun equals(other: Any?): Boolean =
        other is Course &&
                other.id == id

    override fun hashCode(): Int {
        return id
    }
}