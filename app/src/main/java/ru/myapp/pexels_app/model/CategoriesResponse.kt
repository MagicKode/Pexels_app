package ru.myapp.pexels_app.model


import com.google.gson.annotations.SerializedName

data class CategoriesResponse(
    @SerializedName("collections")
    val collections: MutableList<Collection>
) {
    data class Collection(
        @SerializedName("id")
        val id: String?,
        @SerializedName("title")
        val title: String
    )
}