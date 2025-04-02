package ru.myapp.pexels_app.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.myapp.pexels_app.db.repository.impl.CuratedPicsRepositoryImpl
import ru.myapp.pexels_app.model.CuratedPicsResponse
import javax.inject.Inject

@HiltViewModel
class CuratedViewModel @Inject constructor(private val repository: CuratedPicsRepositoryImpl) : ViewModel() {
    private val _curatedPicsList = MutableLiveData<List<CuratedPicsResponse.Photo>?>()
    val curatedPicList: LiveData<List<CuratedPicsResponse.Photo>?> get() = _curatedPicsList

    fun initCuratedPics() {
        viewModelScope.launch {
            try {
                val response = repository.searchCuratedPics()
                _curatedPicsList.postValue(response)
            } catch (e: Exception) {
                Log.e("CuratedViewModel", "Error fetching search results: ${e.message}")
            }
        }
    }
}
