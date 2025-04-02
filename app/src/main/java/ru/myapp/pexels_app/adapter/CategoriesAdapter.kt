package ru.myapp.pexels_app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.myapp.pexels_app.R
import ru.myapp.pexels_app.model.CategoriesResponse

class CategoriesAdapter(
    private val headers: List<CategoriesResponse.Collection>,
    private val itemClick: (CategoriesResponse.Collection) -> Unit
) : RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder>() {

    inner class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val listItem: TextView = view.findViewById(R.id.categoryTitle)

        fun bind(item: CategoriesResponse.Collection) {
            listItem.text = item.title
            listItem.setOnClickListener {
                itemClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesAdapter.CategoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.viewholder_category, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoriesAdapter.CategoryViewHolder, position: Int) {
        holder.bind(headers[position])

    }

    override fun getItemCount(): Int = headers.size
}