package ru.myapp.pexels_app.model


import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "images")
data class DetailPicResponse(

    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    val id: Int?,

    @SerializedName("photographer")
    @ColumnInfo(name = "photographer")
    val photographer: String?,

    @SerializedName("src")
    @ColumnInfo(name = "src")
    val src: Src?,

    @SerializedName("url")
    @ColumnInfo(name = "url")
    val url: String?,
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        TODO("src"),
        parcel.readString()
    )

    data class Src(
        @SerializedName("original")
        @ColumnInfo(name = "original")
        val original: String?
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(photographer)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DetailPicResponse> {
        override fun createFromParcel(parcel: Parcel): DetailPicResponse {
            return DetailPicResponse(parcel)
        }

        override fun newArray(size: Int): Array<DetailPicResponse?> {
            return arrayOfNulls(size)
        }
    }
}