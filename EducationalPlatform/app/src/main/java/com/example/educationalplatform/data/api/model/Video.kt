package com.example.educationalplatform.data.api.model

import com.google.gson.annotations.SerializedName

class Video (
    @SerializedName("preview_url")
    var previewUrl: String,
    @SerializedName("video_url")
    var videoUrl: String,
    @SerializedName("uploaded")
    var uploaded: String,
    @SerializedName("title")
    var title: String,
    @SerializedName("views")
    var views: Int,
    @SerializedName("authors")
    var authors: List<Author>,
)