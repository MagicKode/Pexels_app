package ru.myapp.pexels_app.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import ru.myapp.pexels_app.model.CuratedPicsResponse
import ru.myapp.pexels_app.model.FeaturedCollectionsResponse
import ru.myapp.pexels_app.model.PexelsResponse
import ru.myapp.pexels_app.utils.Constant.API_KEY

interface PexelsApi {

    @Headers("Authorization: ${API_KEY}")
    @GET("v1/collections/featured")
    fun getFeaturedCollections(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Call<FeaturedCollectionsResponse>

    @Headers("Authorization: ${API_KEY}")
    @GET("v1/curated")
    fun getCuratedPicList(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Call<CuratedPicsResponse>

    @Headers("Authorization: ${API_KEY}")
    @GET("v1/search")
    fun getPicList(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Call<PexelsResponse>

}