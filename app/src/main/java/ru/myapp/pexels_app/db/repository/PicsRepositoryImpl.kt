package ru.myapp.pexels_app.db.repository

import ru.myapp.pexels_app.db.dao.PicDao
import ru.myapp.pexels_app.model.CuratedPicsResponse

class PicsRepositoryImpl(private val picDao: PicDao) : PicsRepository {

    override suspend fun getAllPics(): List<CuratedPicsResponse.Photo> {
        return picDao.getAllPics()
    }

    override suspend fun insertPic(image: CuratedPicsResponse.Photo) {
        picDao.insertPic(image)
    }

//    override suspend fun getPicById(id: Int): CuratedPicsResponse.Photo {
//        return picDao.getPicById(id)
//    }
}