package ru.myapp.pexels_app.db.repository.impl

import ru.myapp.pexels_app.db.dao.PicDao
import ru.myapp.pexels_app.db.repository.BookmarkRepository
import ru.myapp.pexels_app.model.CuratedPicsResponse

class BookmarkRepositoryImpl(private val picDao: PicDao): BookmarkRepository {
    override suspend fun getAllPics(): List<CuratedPicsResponse.Photo> {
        return picDao.getAllPics()
    }
}