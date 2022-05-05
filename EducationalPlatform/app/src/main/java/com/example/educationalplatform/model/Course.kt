package com.example.educationalplatform.model

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
    @SerializedName("likes")
    var likes: Int,
    @SerializedName("category")
    var category: Int,
    @SerializedName("publisher_name")
    var publisherName: String,
    @SerializedName("likes_count")
    var likesCount: Int
) {
    override fun equals(other: Any?): Boolean =
        other is Course &&
                other.id == id

    override fun hashCode(): Int {
        return id
    }
}