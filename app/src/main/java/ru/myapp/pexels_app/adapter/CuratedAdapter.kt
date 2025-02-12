package ru.myapp.pexels_app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.myapp.pexels_app.R
import ru.myapp.pexels_app.model.CuratedPicsResponse

class CuratedAdapter(private val pics: List<CuratedPicsResponse.Photo>) :
    RecyclerView.Adapter<CuratedAdapter.CuratedViewHolder>() {

    inner class CuratedViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.picture)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CuratedViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.viewholder_pic, parent, false)
        return CuratedViewHolder(view)
    }

    override fun onBindViewHolder(holder: CuratedViewHolder, position: Int) {
        val photo = pics[position]
        Glide.with(holder.itemView.context)
            .load(photo.src?.original)
            .into(holder.imageView)
    }

    override fun getItemCount(): Int = pics.size
}