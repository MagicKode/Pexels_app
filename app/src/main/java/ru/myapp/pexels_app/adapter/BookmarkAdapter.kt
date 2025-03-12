package ru.myapp.pexels_app.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions
import ru.myapp.pexels_app.R
import ru.myapp.pexels_app.model.CuratedPicsResponse

class BookmarkAdapter(/*private val itemClick: (DetailPicResponse) -> Unit*/) :
    RecyclerView.Adapter<BookmarkAdapter.PicsViewHolder>() {

    var picList = emptyList<CuratedPicsResponse.Photo>()

    inner class PicsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.picFromDb)
        val photographerNameSurname: TextView = itemView.findViewById(R.id.photographerTxt)

        fun bind(photo: CuratedPicsResponse.Photo) {
            Glide.with(itemView.context)
                .asBitmap()
                .load(photo.src?.original)
                .transition(BitmapTransitionOptions.withCrossFade(80))
                .error(R.drawable.placeholder_light)
                .placeholder(R.drawable.placeholder_light)
                .into(imageView)
            photographerNameSurname.text = photo.photographer
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PicsViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.viewholder_bookmark, parent, false)
        return PicsViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookmarkAdapter.PicsViewHolder, position: Int) {
        holder.bind(picList[position])
        holder.photographerNameSurname.text = picList[position].photographer
    }

    override fun getItemCount() = picList.size

    @SuppressLint("NotifyDataSetChanged")
    fun setImages(imageList: List<CuratedPicsResponse.Photo>) {
        picList = imageList
        notifyDataSetChanged()
    }
}