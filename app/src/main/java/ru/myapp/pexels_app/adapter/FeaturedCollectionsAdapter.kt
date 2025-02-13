package ru.myapp.pexels_app.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.myapp.pexels_app.databinding.ViewholderFeaturedCollectionsBinding
import ru.myapp.pexels_app.model.FeaturedCollectionsResponse

class FeaturedCollectionsAdapter(val headers: List<FeaturedCollectionsResponse.Collection>) :
    RecyclerView.Adapter<FeaturedCollectionsAdapter.FeaturedCollectionsViewholder>() {

    inner class FeaturedCollectionsViewholder(val binding: ViewholderFeaturedCollectionsBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeaturedCollectionsAdapter.FeaturedCollectionsViewholder {
        val binding =
            ViewholderFeaturedCollectionsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FeaturedCollectionsViewholder(binding)
    }

    override fun onBindViewHolder(holder: FeaturedCollectionsAdapter.FeaturedCollectionsViewholder, position: Int) {
        val item = headers[position]

        holder.binding.categoryTitle.text = item.title
    }

    override fun getItemCount(): Int = headers.size
}