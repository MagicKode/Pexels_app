package ru.myapp.pexels_app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.myapp.pexels_app.R
import ru.myapp.pexels_app.adapter.listener.OnImageClickListener
import ru.myapp.pexels_app.model.CuratedPicsResponse
import ru.myapp.pexels_app.model.DetailPicResponse

class CuratedAdapter(
    private val pics: List<CuratedPicsResponse.Photo>,
    private val listener: OnImageClickListener
) : RecyclerView.Adapter<CuratedAdapter.CuratedViewHolder>() {

    private var selectedPosition = RecyclerView.NO_POSITION

    inner class CuratedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.picture)

        fun bind(photo: CuratedPicsResponse.Photo) {
            Glide.with(imageView.context)
                .load(photo.src?.original)
                .into(imageView)

            imageView.setOnClickListener {
//                listener.onImageClick()
                selectedPosition = adapterPosition
                notifyDataSetChanged()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CuratedViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.viewholder_pic, parent, false)
        return CuratedViewHolder(view)
    }

    override fun onBindViewHolder(holder: CuratedViewHolder, position: Int) {
        holder.bind(pics[position])
    }

    override fun getItemCount(): Int = pics.size
}