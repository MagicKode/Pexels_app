package ru.myapp.pexels_app

import retrofit2.Response
import retrofit2.http.GET
import ru.myapp.pexels_app.model.CategoryModel

interface ApiInterface {
    @GET("/posts")
    suspend fun getAllCategories(): Response<CategoryModel>
}