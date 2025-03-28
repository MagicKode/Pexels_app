package ru.myapp.pexels_app.viewmodel.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.myapp.pexels_app.db.repository.impl.DetailCuratedPicsRepositoryImpl
import ru.myapp.pexels_app.viewmodel.DetailCuratedPicsViewModel

@Suppress("UNCHECKED_CAST")
class DetailCuratedViewModelFactory(private val picsRepository: DetailCuratedPicsRepositoryImpl) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailCuratedPicsViewModel::class.java)) {
            return DetailCuratedPicsViewModel(picsRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}