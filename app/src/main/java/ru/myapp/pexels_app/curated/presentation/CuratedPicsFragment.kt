package ru.myapp.pexels_app.curated.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ru.myapp.pexels_app.R
import ru.myapp.pexels_app.adapter.CuratedAdapter
import ru.myapp.pexels_app.databinding.FragmentCuratedBinding
import ru.myapp.pexels_app.details.presentation.DetailCuratedPicsFragment
import ru.myapp.pexels_app.model.CuratedPicsResponse
import ru.myapp.pexels_app.viewmodel.CuratedViewModel
import javax.inject.Inject

@AndroidEntryPoint
class CuratedPicsFragment : Fragment() {
    private lateinit var binding: FragmentCuratedBinding
    private lateinit var adapter: CuratedAdapter
    private val picsList = mutableListOf<CuratedPicsResponse.Photo>()
    private val viewModel: CuratedViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
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
            Log.d(
                "CuratedPicsFragment",
                "Received photos: $photos"
            ) // Логирование полученных данных

            picsList.clear()
            picsList.addAll(photos ?: emptyList())
            adapter.notifyDataSetChanged()
        }
    }

    private fun initDetailFragment(photo: CuratedPicsResponse.Photo) {
        val detailCuratedPicsFragment = DetailCuratedPicsFragment.newInstance(photo)
        findNavController().navigate(R.id.detailFragment, detailCuratedPicsFragment.arguments)
    }
}