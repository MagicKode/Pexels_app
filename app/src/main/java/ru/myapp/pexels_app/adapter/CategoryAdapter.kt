package ru.myapp.pexels_app.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.myapp.pexels_app.databinding.ViewholderCategoryBinding
import ru.myapp.pexels_app.model.CategoryModel

class CategoryAdapter(val items: List<String>) :
    RecyclerView.Adapter<CategoryAdapter.Viewholder>() {

    inner class Viewholder(val binding: ViewholderCategoryBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryAdapter.Viewholder {
        val binding =
            ViewholderCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: CategoryAdapter.Viewholder, position: Int) {
        val item = items[position]

        holder.binding.categoryTitle.text = item
    }

    override fun getItemCount(): Int = items.size
}