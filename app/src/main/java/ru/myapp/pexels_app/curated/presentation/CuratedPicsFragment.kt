package ru.myapp.pexels_app.curated.presentation

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
import ru.myapp.pexels_app.R
import ru.myapp.pexels_app.adapter.CuratedAdapter
import ru.myapp.pexels_app.api.RetrofitClient
import ru.myapp.pexels_app.databinding.FragmentCuratedBinding
import ru.myapp.pexels_app.db.repository.impl.CuratedPicsRepositoryImpl
import ru.myapp.pexels_app.details.presentation.DetailCuratedPicsFragment
import ru.myapp.pexels_app.model.CuratedPicsResponse
import ru.myapp.pexels_app.viewmodel.CuratedViewModel
import ru.myapp.pexels_app.viewmodel.viewmodelfactory.CuratedViewModelFactory

class CuratedPicsFragment : Fragment() {

    private lateinit var binding: FragmentCuratedBinding
    private lateinit var adapter: CuratedAdapter
    private lateinit var viewModel: CuratedViewModel
    private val picsList = mutableListOf<CuratedPicsResponse.Photo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val api = RetrofitClient.instance
        val repository = CuratedPicsRepositoryImpl(api)
        val factory = CuratedViewModelFactory(repository)
        viewModel = ViewModelProvider(requireActivity(), factory).get(CuratedViewModel::class)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentCuratedBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = CuratedAdapter(picsList) { photo -> initDetailFragment(photo) }

        initCuratedPics()
        setupRecyclerView()
        observeViewModel()
    }

//    private fun initCuratedPics() {
//        binding.apply {
//            CoroutineScope(Dispatchers.IO).launch {
//                try {
//                    val response = RetrofitClient.instance.getCuratedPicList(1, 30, API_KEY)
//                    withContext(Dispatchers.Main) {
//                        viewPictures.layoutManager =
//                            StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
//                        viewPictures.adapter = adapter
//
//                        picsList.clear()
//                        picsList.addAll(response.photos)
//                        adapter.notifyDataSetChanged()
//                    }
//                } catch (e: Exception) {
//                    Log.e("CuratedPicsFragment", "Exception: ${e.message}")
//                }
//            }
//        }
//    }

    private fun initCuratedPics() {
        viewModel.initCuratedPics()
    }


    private fun setupRecyclerView() {
        binding.apply {
            viewPictures.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
            viewPictures.adapter = adapter
        }
    }

    private fun observeViewModel() {
        viewModel.curatedPicList.observe(viewLifecycleOwner) { photos ->
            Log.d("CuratedPicsFragment", "Received photos: $photos") // Логирование полученных данных

            picsList.clear()
            picsList.addAll(photos ?: emptyList())
            adapter.notifyDataSetChanged()
        }
    }

//    private fun navigateToSearchPicsFragment(query: String) {
//        val fragment = CuratedPicsFragment.newInstance(query)
//        parentFragmentManager.beginTransaction()
//            .replace(R.id.picsContainer, fragment)
//            .commit()
//    }

    private fun initDetailFragment(photo: CuratedPicsResponse.Photo) {
        val detailCuratedPicsFragment = DetailCuratedPicsFragment.newInstance(photo)
        findNavController().navigate(R.id.detailFragment, detailCuratedPicsFragment.arguments)
    }

//    companion object {
//        fun instance(): CuratedPicsFragment {
//            val fragment = CuratedPicsFragment()
//            val args = Bundle().apply {
//                putString("query", query)
//            }
//            fragment.arguments = args
//            return fragment
//        }
//    }
}