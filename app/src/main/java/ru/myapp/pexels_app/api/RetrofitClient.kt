package ru.myapp.pexels_app.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.myapp.pexels_app.utils.Constant.BASE_URL

object RetrofitClient {
    val instance: PexelsApi by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(PexelsApi::class.java)
    }
}
