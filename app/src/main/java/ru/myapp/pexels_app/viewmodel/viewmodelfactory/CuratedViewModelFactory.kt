package ru.myapp.pexels_app.viewmodel.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.myapp.pexels_app.db.repository.impl.CuratedPicsRepositoryImpl
import ru.myapp.pexels_app.viewmodel.CuratedViewModel

@Suppress("UNCHECKED_CAST")
class CuratedViewModelFactory(private val repository: CuratedPicsRepositoryImpl): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CuratedViewModel::class.java)) {
            return CuratedViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}