package com.example.educationalplatform.model

class Course(
    var id: Int,
    var title: String,
    var description: String,
    var created: String,
    var likes: Int,
) {
    override fun equals(other: Any?): Boolean =
        other is Course &&
                other.title == title &&
                other.description == description &&
                other.created == created &&
                other.likes == likes

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + title.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + created.hashCode()
        result = 31 * result + likes
        return result
    }
}