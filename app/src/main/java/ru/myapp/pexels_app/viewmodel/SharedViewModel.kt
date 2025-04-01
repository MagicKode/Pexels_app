package ru.myapp.pexels_app.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel: ViewModel() {
    val isLoading = MutableLiveData<Boolean>()
}