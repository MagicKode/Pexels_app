package ru.myapp.pexels_app.adapter

import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import ru.myapp.pexels_app.R

class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    @SuppressLint("ResourceType")
    val imageView: ImageView = view.findViewById(R.id.picture)
}