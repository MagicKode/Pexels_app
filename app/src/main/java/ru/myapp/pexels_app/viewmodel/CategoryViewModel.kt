package ru.myapp.pexels_app.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.myapp.pexels_app.db.repository.impl.CategoryPicsRepositoryImpl
import ru.myapp.pexels_app.model.CategoriesResponse
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(private val repository: CategoryPicsRepositoryImpl): ViewModel() {
    private val _categoryText = MutableLiveData<MutableList<CategoriesResponse.Collection>?>()
    val categoryText: LiveData<MutableList<CategoriesResponse.Collection>?> get() = _categoryText

    private val _categoryTitle = MutableLiveData<String>()
    val categoryTitle: LiveData<String> get() = _categoryTitle

    fun initSearchCategories() {
        viewModelScope.launch {
            try {
                val response = repository.searchCategories()
                _categoryText.postValue(response)
            } catch (e: Exception) {
                Log.e("CategoryViewModel", "Error fetching search results: ${e.message}")
            }
        }
    }

    fun setSearchText(text: CategoriesResponse.Collection) {
        Log.d("CategoryViewModel", "Setting search text: ${text.title}")
        _categoryTitle.value = text.title
    }
}