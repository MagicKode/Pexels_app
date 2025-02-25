package ru.myapp.pexels_app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.myapp.pexels_app.R
import ru.myapp.pexels_app.model.SearchPicsResponse

class SearchPicsAdapter(val photos: List<SearchPicsResponse.Photo>) :
    RecyclerView.Adapter<SearchPicsAdapter.SearchPicsViewHolder>() {

    inner class SearchPicsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val imageView: ImageView = view.findViewById(R.id.picture)

        fun bind(image: SearchPicsResponse.Photo) {
            Glide.with(imageView.context)
                .load(image.src?.original)
                .into(imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchPicsViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.viewholder_pic, parent, false)
        return SearchPicsViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchPicsViewHolder, position: Int) {
        holder.bind(photos[position])
    }

    override fun getItemCount(): Int = photos.size
}
