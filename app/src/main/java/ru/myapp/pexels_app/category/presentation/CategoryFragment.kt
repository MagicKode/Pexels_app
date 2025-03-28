package ru.myapp.pexels_app.category.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.myapp.pexels_app.adapter.CategoriesAdapter
import ru.myapp.pexels_app.adapter.listener.OnItemClickListener
import ru.myapp.pexels_app.api.RetrofitClient
import ru.myapp.pexels_app.databinding.FragmentCategoryBinding
import ru.myapp.pexels_app.model.CategoriesResponse
import ru.myapp.pexels_app.utils.Constant.API_KEY

class CategoryFragment : Fragment(), OnItemClickListener {

    private lateinit var binding: FragmentCategoryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoryBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initCategories()
    }

    private fun initCategories() {
        binding.apply {
            CoroutineScope(Dispatchers.IO).launch {
                val response = RetrofitClient.instance.getCategories(1, 7, API_KEY)
                withContext(Dispatchers.Main) {
                    viewCategory.layoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                    viewCategory.adapter =
                        CategoriesAdapter(response.collections, this@CategoryFragment)
                }
            }
        }
    }


    override fun onTitleClick(item: CategoriesResponse.Collection) {
        //TODO put title in searchBar
    }
}