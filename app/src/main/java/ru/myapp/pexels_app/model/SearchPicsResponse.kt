package ru.myapp.pexels_app.model

import com.google.gson.annotations.SerializedName

data class SearchPicsResponse(
    @SerializedName("photos")
    val photos: List<Photo>
) {
    data class Photo(
        @SerializedName("id")
        val id: Int?,
        @SerializedName("photographer")
        val photographer: String?,
        @SerializedName("src")
        val src: Src?,
        @SerializedName("url")
        val url: String?
    ) {
        data class Src(
            @SerializedName("original")
            val original: String?
        )
    }
}