package ru.myapp.pexels_app.adapter.listener

import ru.myapp.pexels_app.model.CategoriesResponse

interface OnItemClickListener {
    fun onTitleClick(item: CategoriesResponse.Collection)
}