package ru.myapp.pexels_app.viewmodel.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.myapp.pexels_app.db.repository.impl.SearchPicsRepositoryImpl
import ru.myapp.pexels_app.viewmodel.SearchViewModel

@Suppress("UNCHECKED_CAST")
class SearchViewModelFactory(private val repository: SearchPicsRepositoryImpl) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            return SearchViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}