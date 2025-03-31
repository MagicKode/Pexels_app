package ru.myapp.pexels_app.db.repository

import ru.myapp.pexels_app.model.CuratedPicsResponse

interface DetailCuratedPicsRepository {
    suspend fun insertPic(image: CuratedPicsResponse.Photo)
}