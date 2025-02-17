package ru.myapp.pexels_app.adapter

import ru.myapp.pexels_app.model.FeaturedCollectionsResponse

interface OnItemClickListener {
    fun onItemClick(item: FeaturedCollectionsResponse.Collection)
}