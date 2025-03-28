package ru.myapp.pexels_app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.myapp.pexels_app.db.repository.DetailSearchPicsRepository
import ru.myapp.pexels_app.model.SearchPicsResponse

class DetailSearchPicsViewModel(private val repository: DetailSearchPicsRepository): ViewModel() {

    fun insertPic(pic: SearchPicsResponse.Photo) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertPic(pic)
        }
    }
}