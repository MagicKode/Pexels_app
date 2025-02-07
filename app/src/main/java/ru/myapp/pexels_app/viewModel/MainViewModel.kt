package ru.myapp.pexels_app.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.myapp.pexels_app.model.CategoryModel

class MainViewModel: ViewModel() {

    private val _category = MutableLiveData<MutableList<CategoryModel>>()

    val category: LiveData<MutableList<CategoryModel>> = _category

    fun loadCategory() {

    }

}