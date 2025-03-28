package ru.myapp.pexels_app.db.repository.impl

import ru.myapp.pexels_app.db.dao.PicDao
import ru.myapp.pexels_app.db.repository.DetailSearchPicsRepository
import ru.myapp.pexels_app.model.SearchPicsResponse

class DetailSearchPicsRepositoryImpl(private val picDao: PicDao): DetailSearchPicsRepository {
    override suspend fun insertPic(image: SearchPicsResponse.Photo) {
        return picDao.insertSearchPic(image)
    }
}