package ru.myapp.pexels_app.db.repository.impl

import ru.myapp.pexels_app.api.PexelsApi
import ru.myapp.pexels_app.db.repository.CuratedPicsRepository
import ru.myapp.pexels_app.model.CuratedPicsResponse
import ru.myapp.pexels_app.utils.Constant.API_KEY

class CuratedPicsRepositoryImpl(private val api: PexelsApi): CuratedPicsRepository {
    override suspend fun searchCuratedPics(): List<CuratedPicsResponse.Photo>? {
        val response = api.getCuratedPicList(1, 30, API_KEY)
        return response.photos
    }
}