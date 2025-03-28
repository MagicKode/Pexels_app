package ru.myapp.pexels_app.db.repository

import androidx.lifecycle.LiveData
import ru.myapp.pexels_app.model.SearchPicsResponse

interface SearchPicsRepository {
    suspend fun searchPics(query: String): List<SearchPicsResponse.Photo>?
}