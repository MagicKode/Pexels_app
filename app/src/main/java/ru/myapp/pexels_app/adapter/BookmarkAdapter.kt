package ru.myapp.pexels_app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.myapp.pexels_app.R
import ru.myapp.pexels_app.model.CuratedPicsResponse

class BookmarkAdapter(private val picsList: List<CuratedPicsResponse.Photo>) :
    RecyclerView.Adapter<BookmarkAdapter.PicsViewHolder>() {

    inner class PicsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.picFromDb)
        val textView: TextView = itemView.findViewById(R.id.photographerTxt)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BookmarkAdapter.PicsViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.viewholder_bookmark, parent, false)
        return PicsViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookmarkAdapter.PicsViewHolder, position: Int) {
        val picture = picsList[position]
//        holder.imageView.setIm(picture.src?.original)
        holder.textView.setText(picture.photographer)
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}