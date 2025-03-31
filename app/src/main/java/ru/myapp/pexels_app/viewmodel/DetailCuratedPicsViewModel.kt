package ru.myapp.pexels_app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.myapp.pexels_app.db.repository.impl.DetailCuratedPicsRepositoryImpl
import ru.myapp.pexels_app.model.CuratedPicsResponse

class DetailCuratedPicsViewModel(private val picsRepository: DetailCuratedPicsRepositoryImpl) : ViewModel() {
    fun insertPic(pic: CuratedPicsResponse.Photo) {
        viewModelScope.launch(Dispatchers.IO) {
            picsRepository.insertPic(pic)
        }
    }
}