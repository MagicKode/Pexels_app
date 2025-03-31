package ru.myapp.pexels_app.db.repository.impl

import ru.myapp.pexels_app.api.PexelsApi
import ru.myapp.pexels_app.db.repository.SearchPicsRepository
import ru.myapp.pexels_app.model.SearchPicsResponse
import ru.myapp.pexels_app.utils.Constant.API_KEY

class SearchPicsRepositoryImpl(private val api: PexelsApi) : SearchPicsRepository {
    override suspend fun searchPics(query: String): MutableList<SearchPicsResponse.Photo>? {
        val response = api.searchPics(query, 1, 30, API_KEY)
        return response.photos
    }
}
