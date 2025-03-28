package ru.myapp.pexels_app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.myapp.pexels_app.db.repository.impl.DetailCuratedPicsRepositoryImpl
import ru.myapp.pexels_app.model.CuratedPicsResponse

class DetailCuratedPicsViewModel(private val picsRepository: DetailCuratedPicsRepositoryImpl) : ViewModel() {

    private var allPics = MutableLiveData<List<CuratedPicsResponse.Photo>>()
    val detailPic: LiveData<List<CuratedPicsResponse.Photo>> get() = allPics

    fun getAllPics() {
        viewModelScope.launch {
            allPics.value = picsRepository.getAllPics()
        }
    }

    fun insertPic(pic: CuratedPicsResponse.Photo) {
        viewModelScope.launch(Dispatchers.IO) {
            picsRepository.insertPic(pic)
        }
    }
}