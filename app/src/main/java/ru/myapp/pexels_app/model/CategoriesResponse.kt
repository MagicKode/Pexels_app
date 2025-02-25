package ru.myapp.pexels_app.model


import com.google.gson.annotations.SerializedName

data class CategoriesResponse(
    @SerializedName("collections")
    val collections: List<Collection>,
    @SerializedName("next_page")
    val nextPage: String?,
    @SerializedName("page")
    val page: Int?,
    @SerializedName("per_page")
    val perPage: Int?,
    @SerializedName("prev_page")
    val prevPage: String?,
    @SerializedName("total_results")
    val totalResults: Int?
) {
    data class Collection(
        @SerializedName("description")
        val description: Any?,
        @SerializedName("id")
        val id: String?,
        @SerializedName("media_count")
        val mediaCount: Int?,
        @SerializedName("photos_count")
        val photosCount: Int?,
        @SerializedName("private")
        val `private`: Boolean?,
        @SerializedName("title")
        val title: String?,
        @SerializedName("videos_count")
        val videosCount: Int?
    )
}