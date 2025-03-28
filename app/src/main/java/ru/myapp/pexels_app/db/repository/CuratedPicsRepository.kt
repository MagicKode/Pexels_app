package ru.myapp.pexels_app.db.repository

import ru.myapp.pexels_app.model.CuratedPicsResponse

interface CuratedPicsRepository {
    suspend fun searchCuratedPics(): List<CuratedPicsResponse.Photo>?
}