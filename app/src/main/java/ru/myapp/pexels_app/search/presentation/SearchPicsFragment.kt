package ru.myapp.pexels_app.search.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import ru.myapp.pexels_app.details.presentation.DetailSearchPicsFragment
import ru.myapp.pexels_app.R
import ru.myapp.pexels_app.adapter.SearchPicsAdapter
import ru.myapp.pexels_app.api.RetrofitClient
import ru.myapp.pexels_app.category.presentation.CategoryFragment
import ru.myapp.pexels_app.databinding.FragmentSearchPicsBinding
import ru.myapp.pexels_app.db.repository.impl.SearchPicsRepositoryImpl
import ru.myapp.pexels_app.model.CategoriesResponse
import ru.myapp.pexels_app.model.SearchPicsResponse
import ru.myapp.pexels_app.viewmodel.SearchViewModel
import ru.myapp.pexels_app.viewmodel.viewmodelfactory.SearchViewModelFactory

class SearchPicsFragment : Fragment() {

    private lateinit var binding: FragmentSearchPicsBinding
    private lateinit var adapter: SearchPicsAdapter
    private lateinit var viewModel: SearchViewModel
    private val picsList = mutableListOf<SearchPicsResponse.Photo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchPicsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = SearchPicsAdapter(picsList) { photo -> initDetailFragment(photo) }

        setupRecyclerView()
        observeViewModel()

        val query = arguments?.getString("query") ?: ""
        val searchResult = viewModel.initSearchPics(query)
        if (query.isNotEmpty()) {
            viewModel.initSearchPics(query)
        } else if (query.isNotEmpty() && searchResult == null) {
            initNoResultFoundFragment()
        }
    }

    private fun setupViewModel() {
        val api = RetrofitClient.instance
        val repository = SearchPicsRepositoryImpl(api)
        val factory = SearchViewModelFactory(repository)
        viewModel = ViewModelProvider(requireActivity(), factory).get(SearchViewModel::class.java)
    }


    private fun setupRecyclerView() {
        binding.apply {
            searchPics.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
            searchPics.adapter = adapter
        }
    }

    private fun observeViewModel() {
        viewModel.searchPhotos.observe(viewLifecycleOwner) { photos ->
            Log.d("SearchPicsFragment", "Received photos: $photos") // Логирование полученных данных
            picsList.clear()
            picsList.addAll(photos ?: emptyList())
            adapter.notifyDataSetChanged()
        }
    }

    private fun initDetailFragment(photo: SearchPicsResponse.Photo) {
        val detailFragment = DetailSearchPicsFragment.newSearchInstance(photo)
        findNavController().navigate(R.id.detailSearchPicsFragment, detailFragment.arguments)
    }

    private fun initNoResultFoundFragment() {
        Log.d("BookmarkPicsFragment", "Replacing fragment with EmptyBookmarkFragment")
        val noResultFoundFragment = NoResultFoundFragment()
        parentFragmentManager.beginTransaction()
            .replace(R.id.picsContainer, noResultFoundFragment)
            .commit()
    }

    companion object {
        fun newInstance(query: String): SearchPicsFragment {
            val fragment = SearchPicsFragment()
            val args = Bundle().apply {
                putString("query", query)
            }
            fragment.arguments = args
            return fragment
        }
    }
}