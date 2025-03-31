package ru.myapp.pexels_app.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.myapp.pexels_app.db.repository.impl.SearchPicsRepositoryImpl
import ru.myapp.pexels_app.model.SearchPicsResponse

class SearchViewModel(private val repository: SearchPicsRepositoryImpl) : ViewModel() {
    private val _searchPhotos = MutableLiveData<MutableList<SearchPicsResponse.Photo>?>()
    val searchPhotos: LiveData<MutableList<SearchPicsResponse.Photo>?> get() = _searchPhotos

    fun initSearchPics(query: String) {
        viewModelScope.launch {
            try {
                val response = repository.searchPics(query)
                _searchPhotos.postValue(response)
            } catch (e: Exception) {
                Log.e("SearchViewModel", "Error fetching search results: ${e.message}")
            }
        }
    }
}