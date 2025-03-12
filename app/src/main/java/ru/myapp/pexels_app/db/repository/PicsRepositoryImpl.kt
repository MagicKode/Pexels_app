package ru.myapp.pexels_app.db.repository

import androidx.lifecycle.LiveData
import ru.myapp.pexels_app.db.dao.PicDao
import ru.myapp.pexels_app.model.CuratedPicsResponse

class PicsRepositoryImpl(private val picDao: PicDao) : PicsRepository {

    override fun getAllPics(): LiveData<List<CuratedPicsResponse.Photo>> {
        return picDao.getAllPics()
    }

    override suspend fun insertPic(pic: CuratedPicsResponse.Photo) {
        picDao.insertPic(pic)
    }

    override suspend fun getPicById(id: CuratedPicsResponse.Photo) {
        picDao.getPicById(id)
    }
}