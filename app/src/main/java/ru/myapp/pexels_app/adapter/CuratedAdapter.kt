package ru.myapp.pexels_app.adapter

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import ru.myapp.pexels_app.R
import ru.myapp.pexels_app.model.CuratedPicsResponse

class CuratedAdapter(
    private val pics: MutableList<CuratedPicsResponse.Photo>,
    private val itemClick: (CuratedPicsResponse.Photo) -> Unit
) : RecyclerView.Adapter<CuratedAdapter.CuratedViewHolder>() {

    inner class CuratedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.picture)

        fun bind(photo: CuratedPicsResponse.Photo) {

            Glide.with(itemView.context)
                .asBitmap()
                .load(photo.src.original)
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .transition(BitmapTransitionOptions.withCrossFade(80))
                .error(R.drawable.placeholder_light)
                .placeholder(R.drawable.placeholder_light)
                .listener(object : RequestListener<Bitmap> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Bitmap>,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }

                    override fun onResourceReady(
                        resource: Bitmap,
                        model: Any,
                        target: Target<Bitmap>?,
                        dataSource: DataSource,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }
                })
                .into(imageView)

            itemView.setOnClickListener {
                itemClick(photo)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CuratedViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.viewholder_pic, parent, false)
        return CuratedViewHolder(view)
    }

    override fun onBindViewHolder(holder: CuratedViewHolder, position: Int) {
        holder.bind(pics[position])
    }

    override fun getItemCount(): Int = pics.size
}