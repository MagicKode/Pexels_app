package ru.myapp.pexels_app.db.repository

import ru.myapp.pexels_app.model.CuratedPicsResponse

interface PicsRepository {
    suspend fun getAllPics(): List<CuratedPicsResponse.Photo>
    suspend fun insertPic(image: CuratedPicsResponse.Photo)
}