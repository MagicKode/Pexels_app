package ru.myapp.pexels_app.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.myapp.pexels_app.db.PexelsDatabase
import ru.myapp.pexels_app.db.repository.PicsRepositoryImpl
import ru.myapp.pexels_app.model.CuratedPicsResponse

class DetailViewModel(application: Application) : AndroidViewModel(application) {
    private var picsRepository: PicsRepositoryImpl
    private var allPics: LiveData<List<CuratedPicsResponse.Photo>>


    init {
        val picDao = PexelsDatabase.getDatabase(application).getPicDao()
        picsRepository = PicsRepositoryImpl(picDao)
        allPics = MutableLiveData()
    }

    fun getAllPics(): LiveData<List<CuratedPicsResponse.Photo>> {
        return picsRepository.getAllPics()
    }

    fun insertPic(pic: CuratedPicsResponse.Photo) {
        viewModelScope.launch(Dispatchers.IO) {
            picsRepository.insertPic(pic)
        }
    }
}