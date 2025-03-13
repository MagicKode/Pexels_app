package ru.myapp.pexels_app.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "curated_pics")
data class CuratedPicsResponse(
    @ColumnInfo(name = "photos")
    @SerializedName("photos")
    val photos: List<Photo>
) {
    @Entity(tableName = "curated_photo")
    data class Photo(
        @PrimaryKey(autoGenerate = true)
        @SerializedName("id")
        val id: Int?,

        @ColumnInfo(name = "photographer")
        @SerializedName("photographer")
        val photographer: String?,

        @ColumnInfo(name = "src")
        @SerializedName("src")
        val src: Src,

        @ColumnInfo(name = "url")
        @SerializedName("url")
        val url: String?,
    ) : Parcelable {
        data class Src(
            @ColumnInfo(name = "original")
            @SerializedName("original")
            val original: String,
        )

        constructor(parcel: Parcel) : this(
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readString(),
            TODO("src"),
            parcel.readString()
        )

        override fun describeContents(): Int {
            return 0
        }

        override fun writeToParcel(dest: Parcel, flags: Int) {
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
