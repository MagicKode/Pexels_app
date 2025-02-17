package ru.myapp.pexels_app.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.myapp.pexels_app.R
import ru.myapp.pexels_app.model.FeaturedCollectionsResponse

class FeaturedCollectionsAdapter(
    private val headers: List<FeaturedCollectionsResponse.Collection>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<FeaturedCollectionsAdapter.FeaturedCollectionsViewholder>() {

    private var selectedPosition = RecyclerView.NO_POSITION

    inner class FeaturedCollectionsViewholder(view: View) : RecyclerView.ViewHolder(view) {

        val listItem: TextView = view.findViewById(R.id.categoryTitle)

        fun bind(item: FeaturedCollectionsResponse.Collection) {
            listItem.setOnClickListener {
                listener.onItemClick(item)
                selectedPosition = adapterPosition
                notifyDataSetChanged()
            }
        }
//        init {
//            view.setOnClickListener {
//
//            }
//        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            FeaturedCollectionsAdapter.FeaturedCollectionsViewholder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.viewholder_featured_collections, parent, false)

        return FeaturedCollectionsViewholder(view)
    }

    override fun onBindViewHolder(
        holder: FeaturedCollectionsAdapter.FeaturedCollectionsViewholder,
        position: Int
    ) {
        val item = headers[position]
        holder.listItem.text = item.title  //загрузка заголовка

        if (position == selectedPosition) {
            holder.itemView.setBackgroundColor(Color.RED)
            holder.listItem.setTextColor(Color.WHITE)
        } else {
            holder.itemView.setBackgroundResource(R.color.grey)
            holder.listItem.setTextColor(Color.BLACK)
        }

        holder.bind(item) // нажатие на заголовок



    }

    override fun getItemCount(): Int = headers.size
}