package ru.myapp.pexels_app.api

import androidx.lifecycle.LiveData
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query
import ru.myapp.pexels_app.model.CategoriesResponse
import ru.myapp.pexels_app.model.CuratedPicsResponse
import ru.myapp.pexels_app.model.DetailPicResponse
import ru.myapp.pexels_app.model.SearchPicsResponse

interface PexelsApi {

    @GET("v1/collections/featured")
    suspend fun getCategories(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
        @Header("Authorization") apiKey: String
    ): CategoriesResponse

    @GET("v1/curated")
    suspend fun getCuratedPicList(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
        @Header("Authorization") apiKey: String
    ): CuratedPicsResponse

    @GET("v1/search")
    suspend fun searchPics(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
        @Header("Authorization") apiKey: String
    ): SearchPicsResponse

    @GET("v1/photos/{id}")
    suspend fun getDetailPic(
        @Path("id") id: Int,
        @Header("Authorization") apiKey: String
    ): DetailPicResponse
}