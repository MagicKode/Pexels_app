package ru.myapp.pexels_app.viewmodel.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.myapp.pexels_app.db.repository.DetailSearchPicsRepository
import ru.myapp.pexels_app.viewmodel.DetailSearchPicsViewModel

@Suppress("UNCHECKED_CAST")
class DetailSearchViewModelFactory(private val picsRepository: DetailSearchPicsRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailSearchPicsViewModel::class.java)) {
            return DetailSearchPicsViewModel(picsRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}