package ru.myapp.pexels_app.db.repository.impl

import ru.myapp.pexels_app.db.dao.PicDao
import ru.myapp.pexels_app.db.repository.DetailCuratedPicsRepository
import ru.myapp.pexels_app.model.CuratedPicsResponse

class DetailCuratedPicsRepositoryImpl(private val picDao: PicDao) : DetailCuratedPicsRepository {
    override suspend fun insertPic(image: CuratedPicsResponse.Photo) {
        picDao.insertPic(image)
    }
}