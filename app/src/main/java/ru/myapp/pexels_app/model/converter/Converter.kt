package ru.myapp.pexels_app.model.converter

import androidx.room.Entity
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.myapp.pexels_app.model.CuratedPicsResponse
import ru.myapp.pexels_app.model.DetailPicResponse

class Converter {
    private val gson = Gson()

    @TypeConverter
    fun fromDetailSrc(src: DetailPicResponse.Src?): String? {
        return gson.toJson(src)
    }

    @TypeConverter
    fun toDetailSrc(srcString: String?): DetailPicResponse.Src? {
        val type = object : TypeToken<DetailPicResponse.Src?>() {}.type
        return gson.fromJson(srcString, type)
    }

    @TypeConverter
    fun fromCuratedSrc(src: CuratedPicsResponse.Photo.Src?): String? {
        return gson.toJson(src)
    }

    @TypeConverter
    fun toCuratedSrc(srcString: String?): CuratedPicsResponse.Photo.Src? {
        val type = object : TypeToken<CuratedPicsResponse.Photo.Src?>() {}.type
        return gson.fromJson(srcString, type)
    }
}