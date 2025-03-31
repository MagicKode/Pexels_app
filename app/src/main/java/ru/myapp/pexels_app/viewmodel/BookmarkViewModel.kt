package ru.myapp.pexels_app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.myapp.pexels_app.db.repository.impl.BookmarkRepositoryImpl
import ru.myapp.pexels_app.model.CuratedPicsResponse

class BookmarkViewModel(private val repository: BookmarkRepositoryImpl) : ViewModel() {
    private var _detailPics = MutableLiveData<List<CuratedPicsResponse.Photo>>()
    val detailPic: LiveData<List<CuratedPicsResponse.Photo>> get() = _detailPics

    fun getAllPics() {
        viewModelScope.launch {
            _detailPics.value = repository.getAllPics()
        }
    }
}