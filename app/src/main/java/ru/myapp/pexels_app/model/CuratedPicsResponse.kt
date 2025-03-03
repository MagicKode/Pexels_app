package ru.myapp.pexels_app.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class CuratedPicsResponse(
    @SerializedName("next_page")
    val nextPage: String?,
    @SerializedName("page")
    val page: Int?,
    @SerializedName("per_page")
    val perPage: Int?,
    @SerializedName("photos")
    val photos: List<Photo>
) {
    data class Photo(
        @SerializedName("alt")
        val alt: String?,
        @SerializedName("avg_color")
        val avgColor: String?,
        @SerializedName("height")
        val height: Int?,
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
        val src: Src,
        @SerializedName("url")
        val url: String?,
        @SerializedName("width")
        val width: Int?
    ) : Parcelable {
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
            val original: String,
            @SerializedName("portrait")
            val portrait: String?,
            @SerializedName("small")
            val small: String?,
            @SerializedName("tiny")
            val tiny: String?
        )

        constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
            parcel.readString(),
            parcel.readValue(Long::class.java.classLoader) as? Long,
            parcel.readString(),
            TODO("src"),
            parcel.readString(),
            parcel.readValue(Int::class.java.classLoader) as? Int
        )

        override fun describeContents(): Int {
            TODO("Not yet implemented")
        }

        override fun writeToParcel(p0: Parcel, p1: Int) {
            TODO("Not yet implemented")
        }

        companion object CREATOR : Parcelable.Creator<Photo> {
            override fun createFromParcel(parcel: Parcel): Photo {
                return Photo(parcel)
            }

            override fun newArray(size: Int): Array<Photo?> {
                return arrayOfNulls(size)
            }
        }
    }
}
