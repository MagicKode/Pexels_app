package ru.myapp.pexels_app.db.repository

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import ru.myapp.pexels_app.db.dao.PicDao
import ru.myapp.pexels_app.model.CuratedPicsResponse

class PicsRepositoryImpl(private val picDao: PicDao) : PicsRepository {

    override fun getAllPics(): Flow<List<CuratedPicsResponse.Photo>> {
        return picDao.getAllPics()
    }

    override suspend fun insertPic(image: CuratedPicsResponse.Photo) {
        picDao.insertPic(image)
    }

//    override suspend fun getPicById(id: CuratedPicsResponse.Photo) {
//        picDao.getPicById(id)
//    }
}