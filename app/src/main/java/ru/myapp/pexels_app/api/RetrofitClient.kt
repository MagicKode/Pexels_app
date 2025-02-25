package ru.myapp.pexels_app.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.myapp.pexels_app.utils.Constant.BASE_URL

object RetrofitClient {
    val instance: PexelsApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PexelsApi::class.java)
    }
}
