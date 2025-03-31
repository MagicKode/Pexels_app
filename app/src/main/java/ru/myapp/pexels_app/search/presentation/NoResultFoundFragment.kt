package ru.myapp.pexels_app.search.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.myapp.pexels_app.adapter.SearchPicsAdapter
import ru.myapp.pexels_app.api.RetrofitClient
import ru.myapp.pexels_app.databinding.FragmentNoResultFoundBinding
import ru.myapp.pexels_app.db.repository.impl.SearchPicsRepositoryImpl
import ru.myapp.pexels_app.model.SearchPicsResponse
import ru.myapp.pexels_app.viewmodel.SearchViewModel
import ru.myapp.pexels_app.viewmodel.viewmodelfactory.SearchViewModelFactory

class NoResultFoundFragment: Fragment() {

    private lateinit var binding: FragmentNoResultFoundBinding
    private lateinit var viewModel: SearchViewModel
    private lateinit var adapter: SearchPicsAdapter
    private val picsList = mutableListOf<SearchPicsResponse.Photo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewModel()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentNoResultFoundBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = SearchPicsAdapter(picsList) {

            observeViewModel()
            initRepeatImageRequest()
        }
    }

    private fun setupViewModel() {
        val api = RetrofitClient.instance
        val picsRepository = SearchPicsRepositoryImpl(api)
        val factory = SearchViewModelFactory(picsRepository)
        viewModel = ViewModelProvider(this, factory).get(SearchViewModel::class.java)
    }

    private fun observeViewModel() {
        viewModel.searchPhotos.observe(viewLifecycleOwner) { pic ->
            adapter = SearchPicsAdapter(picsList) {
                adapter.updatePicsList(pic!!)
            }
        }
    }
    private fun initRepeatImageRequest() {
        binding.apply {
            val query = arguments?.getString("query") ?: ""
            exploreImageBtn.setOnClickListener {
                viewModel.initSearchPics(query)
                viewModel.searchPhotos
            }
        }
    }
}