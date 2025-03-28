package ru.myapp.pexels_app.db.repository

import ru.myapp.pexels_app.model.SearchPicsResponse

interface DetailSearchPicsRepository {
    suspend fun insertPic(image: SearchPicsResponse.Photo)
}