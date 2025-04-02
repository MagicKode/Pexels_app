package ru.myapp.pexels_app.category.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ru.myapp.pexels_app.adapter.CategoriesAdapter
import ru.myapp.pexels_app.databinding.FragmentCategoryBinding
import ru.myapp.pexels_app.model.CategoriesResponse
import ru.myapp.pexels_app.viewmodel.CategoryViewModel
import javax.inject.Inject

@AndroidEntryPoint
class CategoryFragment : Fragment() {

    private lateinit var binding: FragmentCategoryBinding
    private lateinit var adapter: CategoriesAdapter
    private val categories = mutableListOf<CategoriesResponse.Collection>()
    private val viewModel: CategoryViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCategoryBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = CategoriesAdapter(categories) { category ->
            viewModel.setSearchText(category)
        }
        initCategoryPics()
        setupRecyclerView()
        observeViewModel()
    }
    private fun initCategoryPics() {
        viewModel.initSearchCategories()
    }

    private fun setupRecyclerView() {
        binding.apply {
            viewCategory.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            viewCategory.adapter = adapter
        }
    }

    private fun observeViewModel() {
        viewModel.categoryText.observe(viewLifecycleOwner) { category ->
            Log.d("CategoriesPicsFragment", "Received photos: $category") // Логирование полученных данных

            categories.clear()
            categories.addAll(category ?: emptyList())
            adapter.notifyDataSetChanged()
        }
    }
}