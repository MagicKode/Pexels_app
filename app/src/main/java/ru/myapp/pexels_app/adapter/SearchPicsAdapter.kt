package ru.myapp.pexels_app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ImageView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions
import ru.myapp.pexels_app.R
import ru.myapp.pexels_app.model.CuratedPicsResponse
import ru.myapp.pexels_app.model.SearchPicsResponse

class SearchPicsAdapter(
    private var photos: MutableList<SearchPicsResponse.Photo>,
    private val itemClick: (SearchPicsResponse.Photo) -> Unit
) : ListAdapter<SearchPicsResponse.Photo, SearchPicsAdapter.SearchPicsViewHolder>(PhotoDiffCallback()) {

    inner class SearchPicsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.searchPicture)

        fun bind(image: SearchPicsResponse.Photo) {
            Glide.with(imageView.context)
                .asBitmap()
                .load(image.src.original)
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .transition(BitmapTransitionOptions.withCrossFade(80))
                .error(R.drawable.placeholder_light)
                .placeholder(R.drawable.placeholder_light)
                .into(imageView)

            itemView.setOnClickListener {
                itemClick(image)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchPicsViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.viewholder_search_pic, parent, false)
        return SearchPicsViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchPicsViewHolder, position: Int) {
        holder.bind(photos[position])
    }

    override fun getItemCount(): Int = photos.size

    fun updatePicsList(newPhotos: MutableList<SearchPicsResponse.Photo>) {
        photos = newPhotos
        notifyDataSetChanged()
    }
}

class PhotoDiffCallback : DiffUtil.ItemCallback<SearchPicsResponse.Photo>(){
    override fun areItemsTheSame(oldItem: SearchPicsResponse.Photo, newItem: SearchPicsResponse.Photo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: SearchPicsResponse.Photo, newItem: SearchPicsResponse.Photo): Boolean {
        return oldItem == newItem
    }
}
