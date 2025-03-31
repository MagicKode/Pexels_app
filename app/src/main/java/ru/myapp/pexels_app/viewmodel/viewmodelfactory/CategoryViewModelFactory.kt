package ru.myapp.pexels_app.viewmodel.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.myapp.pexels_app.db.repository.impl.CategoryPicsRepositoryImpl
import ru.myapp.pexels_app.viewmodel.CategoryViewModel

@Suppress("UNCHECKED_CAST")
class CategoryViewModelFactory(private val repository: CategoryPicsRepositoryImpl): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CategoryViewModel::class.java)) {
            return CategoryViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}