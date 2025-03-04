package ru.myapp.pexels_app.model


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "images")
data class DetailPicResponse(
    @SerializedName("alt")
    val alt: String?,
    @SerializedName("avg_color")
    val avgColor: String?,
    @SerializedName("height")
    val height: Int?,

    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    val id: Int?,

    @SerializedName("liked")
    val liked: Boolean?,

    @SerializedName("photographer")
    val photographer: String?,
    @SerializedName("photographer_id")
    val photographerId: Long?,
    @SerializedName("photographer_url")
    val photographerUrl: String?,
    @SerializedName("src")
    val src: Src?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("width")
    val width: Int?
) {
    data class Src(
        @SerializedName("landscape")
        val landscape: String?,
        @SerializedName("large")
        val large: String?,
        @SerializedName("large2x")
        val large2x: String?,
        @SerializedName("medium")
        val medium: String?,
        @SerializedName("original")
        val original: String?,
        @SerializedName("portrait")
        val portrait: String?,
        @SerializedName("small")
        val small: String?,
        @SerializedName("tiny")
        val tiny: String?
    )
}