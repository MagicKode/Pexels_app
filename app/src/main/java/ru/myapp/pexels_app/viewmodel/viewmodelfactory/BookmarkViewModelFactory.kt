package ru.myapp.pexels_app.viewmodel.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.myapp.pexels_app.db.repository.impl.BookmarkRepositoryImpl
import ru.myapp.pexels_app.viewmodel.BookmarkViewModel

@Suppress("UNCHECKED_CAST")
class BookmarkViewModelFactory(private val repository: BookmarkRepositoryImpl): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BookmarkViewModel::class.java)) {
            return BookmarkViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}