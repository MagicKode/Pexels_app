package ru.myapp.pexels_app.adapter.listener

import ru.myapp.pexels_app.model.CuratedPicsResponse
import ru.myapp.pexels_app.model.DetailPicResponse

interface OnImageClickListener {
    fun onImageClick(pic: CuratedPicsResponse.Photo)
}