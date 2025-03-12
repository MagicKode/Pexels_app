package ru.myapp.pexels_app.db.repository

import androidx.lifecycle.LiveData
import ru.myapp.pexels_app.model.CuratedPicsResponse

interface PicsRepository {
    fun getAllPics(): LiveData<List<CuratedPicsResponse.Photo>>
    suspend fun insertPic(image: CuratedPicsResponse.Photo/*, onSuccess:() -> Unit*/)
    suspend fun getPicById(id: CuratedPicsResponse.Photo)
}