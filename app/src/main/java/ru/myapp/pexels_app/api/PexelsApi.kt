package ru.myapp.pexels_app.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query
import ru.myapp.pexels_app.model.CuratedPicsResponse
import ru.myapp.pexels_app.model.FeaturedCollectionsResponse
import ru.myapp.pexels_app.model.SearchPicsResponse
import ru.myapp.pexels_app.utils.Constant.API_KEY

interface PexelsApi {

    @GET("v1/collections/featured")
    fun getFeaturedCollections(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
        @Header("Authorization") apiKey: String
    ): Call<FeaturedCollectionsResponse>


    @GET("v1/curated")
    fun getCuratedPicList(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
        @Header("Authorization") apiKey: String
    ): Call<CuratedPicsResponse>

    @GET("v1/search")
    fun searchPics(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
        @Header("Authorization") apiKey: String
    ): Call<SearchPicsResponse>

}