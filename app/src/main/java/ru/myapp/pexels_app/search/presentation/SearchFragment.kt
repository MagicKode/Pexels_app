package ru.myapp.pexels_app.search.presentation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.myapp.pexels_app.R
import ru.myapp.pexels_app.adapter.SearchPicsAdapter
import ru.myapp.pexels_app.api.RetrofitClient
import ru.myapp.pexels_app.databinding.FragmentSearchBinding
import ru.myapp.pexels_app.db.repository.impl.SearchPicsRepositoryImpl
import ru.myapp.pexels_app.curated.presentation.CuratedPicsFragment
import ru.myapp.pexels_app.model.SearchPicsResponse
import ru.myapp.pexels_app.utils.Constant.API_KEY
import ru.myapp.pexels_app.viewmodel.SearchViewModel
import ru.myapp.pexels_app.viewmodel.viewmodelfactory.SearchViewModelFactory

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var viewModel: SearchViewModel
    private lateinit var adapter: SearchPicsAdapter
    private val picsList = mutableListOf<SearchPicsResponse.Photo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val api = RetrofitClient.instance
        val repository = SearchPicsRepositoryImpl(api)
        val factory = SearchViewModelFactory(repository)
        viewModel = ViewModelProvider(requireActivity(), factory).get(SearchViewModel::class.java)

        adapter = SearchPicsAdapter(picsList) { photo ->

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSearchPicsList()
    }

    private fun initSearchPicsList() {
        binding.apply {
            searchBarTxt.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    val query = s.toString()
                    if (query.isNotEmpty()) {
                        initCleanSearchTxt()
                        initSearchPics(query)
                    } else {
                        cleanTxtBtn.visibility = View.GONE
                        initCuratedPics()
                    }
                }

                override fun afterTextChanged(s: Editable?) {}
            })
        }
    }

    private fun initSearchPics(query: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = RetrofitClient.instance.searchPics(query, 1, 30, API_KEY)
                Log.d("SearchFragment", "Response: $response") // Логирование данных
                withContext(Dispatchers.Main) {
                    if (response.photos.isNotEmpty()) {
                        picsList.clear()
                        picsList.addAll(response.photos)
                        navigateToSearchPicsFragment(query)
                        adapter.updatePicsList(picsList)
                        adapter.notifyDataSetChanged()
                    } else {
                        Log.d("SearchFragment", "No photos found for query: $query")
                    }
                }
            } catch (e: Exception) {
                Log.e("SearchFragment", "Exception: ${e.message}")
            }
        }
    }

    private fun navigateToSearchPicsFragment(query: String) {
        val fragment = SearchPicsFragment.newInstance(query)
        parentFragmentManager.beginTransaction()
            .replace(R.id.picsContainer, fragment)
            .commit()
    }


    private fun initCleanSearchTxt() {
        binding.apply {
            cleanTxtBtn.visibility = View.VISIBLE
            cleanTxtBtn.setOnClickListener {
                searchBarTxt.text?.clear()
            }
        }
    }

    private fun initCuratedPics() {
        val fragment = CuratedPicsFragment()
        parentFragmentManager.beginTransaction()
            .replace(R.id.picsContainer, fragment)
            .commit()
    }
}