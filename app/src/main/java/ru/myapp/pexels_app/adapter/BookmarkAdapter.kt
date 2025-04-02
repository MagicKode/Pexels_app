package ru.myapp.pexels_app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions
import ru.myapp.pexels_app.R
import ru.myapp.pexels_app.model.CuratedPicsResponse

class BookmarkAdapter(
    private var picList: List<CuratedPicsResponse.Photo>,
    private val itemClick: (CuratedPicsResponse.Photo) -> Unit
) : RecyclerView.Adapter<BookmarkAdapter.BookmarkViewHolder>() {

    inner class BookmarkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.picFromDb)
        val photographerNameSurname: TextView = itemView.findViewById(R.id.photographerTxt)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.viewholder_bookmark, parent, false)
        return BookmarkViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookmarkAdapter.BookmarkViewHolder, position: Int) {
        val photo = picList[position]
        Glide.with(holder.itemView.context)
            .asBitmap()
            .load(photo.src.original)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(false)
            .transition(BitmapTransitionOptions.withCrossFade(80))
            .error(R.drawable.placeholder_light)
            .placeholder(R.drawable.placeholder_light)
            .into(holder.imageView)
        holder.photographerNameSurname.text = photo.photographer

        holder.imageView.setOnClickListener {
            itemClick(photo)
        }
    }

    override fun getItemCount() = picList.size

    fun updateList(newPhotos: List<CuratedPicsResponse.Photo>) {
        picList = newPhotos
        notifyDataSetChanged()
    }
}
