package ru.myapp.pexels_app.category.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import ru.myapp.pexels_app.adapter.CategoriesAdapter
import ru.myapp.pexels_app.api.RetrofitClient
import ru.myapp.pexels_app.databinding.FragmentCategoryBinding
import ru.myapp.pexels_app.db.repository.impl.CategoryPicsRepositoryImpl
import ru.myapp.pexels_app.model.CategoriesResponse
import ru.myapp.pexels_app.viewmodel.CategoryViewModel
import ru.myapp.pexels_app.viewmodel.viewmodelfactory.CategoryViewModelFactory

class CategoryFragment : Fragment() {

    private lateinit var binding: FragmentCategoryBinding
    private lateinit var viewModel: CategoryViewModel
    private lateinit var adapter: CategoriesAdapter
    private val categories = mutableListOf<CategoriesResponse.Collection>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewModel()
    }

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

    private fun setupViewModel() {
        val api = RetrofitClient.instance
        val repository = CategoryPicsRepositoryImpl(api)
        val factory = CategoryViewModelFactory(repository)
        viewModel = ViewModelProvider(requireActivity(), factory).get(CategoryViewModel::class)
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