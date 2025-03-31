package ru.myapp.pexels_app.db.repository

import ru.myapp.pexels_app.model.CategoriesResponse

interface CategoryPicsRepository {
    suspend fun searchCategories(): List<CategoriesResponse.Collection>?
}