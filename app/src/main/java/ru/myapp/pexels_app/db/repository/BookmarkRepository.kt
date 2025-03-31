package ru.myapp.pexels_app.db.repository

import ru.myapp.pexels_app.model.CuratedPicsResponse

interface BookmarkRepository {
    suspend fun getAllPics(): List<CuratedPicsResponse.Photo>
}