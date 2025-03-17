package ru.myapp.pexels_app.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.myapp.pexels_app.db.repository.PicsRepositoryImpl

@Suppress("UNCHECKED_CAST")
class DetailViewModelFactory(private val picsRepository: PicsRepositoryImpl) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(picsRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}