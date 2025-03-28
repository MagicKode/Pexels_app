package ru.myapp.pexels_app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.myapp.pexels_app.R
import ru.myapp.pexels_app.adapter.listener.OnItemClickListener
import ru.myapp.pexels_app.model.CategoriesResponse

class CategoriesAdapter(
    private val headers: List<CategoriesResponse.Collection>,
    private val itemClick: OnItemClickListener
) : RecyclerView.Adapter<CategoriesAdapter.CategoryViewholder>() {

    private var selectedPosition = RecyclerView.NO_POSITION

    inner class CategoryViewholder(view: View) : RecyclerView.ViewHolder(view) {
        val listItem: TextView = view.findViewById(R.id.categoryTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesAdapter.CategoryViewholder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.viewholder_category, parent, false)
        return CategoryViewholder(view)
    }

    override fun onBindViewHolder(holder: CategoriesAdapter.CategoryViewholder, position: Int) {
        val item = headers[position]
        holder.listItem.text = item.title
        holder.listItem.setOnClickListener {
            itemClick.onTitleClick(item)
        }

//        if (position == selectedPosition) {
//            holder.itemView.setBackgroundColor(Color.RED)
//            holder.listItem.setTextColor(Color.WHITE)
//        } else {
//            holder.itemView.setBackgroundResource(R.color.grey)
//            holder.listItem.setTextColor(Color.BLACK)
//        }
    }

    override fun getItemCount(): Int = headers.size
}