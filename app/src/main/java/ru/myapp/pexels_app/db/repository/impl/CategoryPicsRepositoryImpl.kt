package ru.myapp.pexels_app.db.repository.impl

import ru.myapp.pexels_app.api.PexelsApi
import ru.myapp.pexels_app.db.repository.CategoryPicsRepository
import ru.myapp.pexels_app.model.CategoriesResponse
import ru.myapp.pexels_app.utils.Constant.API_KEY

class CategoryPicsRepositoryImpl(private val api: PexelsApi): CategoryPicsRepository {
    override suspend fun searchCategories(): MutableList<CategoriesResponse.Collection>? {
        val response = api.getCategories(1, 7, API_KEY)
        return response.collections
    }
}