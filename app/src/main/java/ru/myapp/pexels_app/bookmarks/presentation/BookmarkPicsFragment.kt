package ru.myapp.pexels_app.bookmarks.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import ru.myapp.pexels_app.R
import ru.myapp.pexels_app.adapter.BookmarkAdapter
import ru.myapp.pexels_app.databinding.FragmentBookmarkPicsBinding
import ru.myapp.pexels_app.db.PexelsDatabase
import ru.myapp.pexels_app.db.repository.impl.BookmarkRepositoryImpl
import ru.myapp.pexels_app.model.CuratedPicsResponse
import ru.myapp.pexels_app.viewmodel.BookmarkViewModel
import ru.myapp.pexels_app.viewmodel.viewmodelfactory.BookmarkViewModelFactory

class BookmarkPicsFragment : Fragment() {

    private lateinit var binding: FragmentBookmarkPicsBinding
    private lateinit var adapter: BookmarkAdapter
    private lateinit var viewModel: BookmarkViewModel
    private val picsList = mutableListOf<CuratedPicsResponse.Photo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBookmarkPicsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        viewModel.getAllPics()
        observeViewModel()

    }

    private fun setupViewModel() {
        val picDao = PexelsDatabase.getDatabase(requireContext()).getPicDao()
        val picsRepository = BookmarkRepositoryImpl(picDao)
        val factory = BookmarkViewModelFactory(picsRepository)
        viewModel = ViewModelProvider(this, factory).get(BookmarkViewModel::class.java)
    }

    private fun setupRecyclerView() {
        adapter = BookmarkAdapter(picsList) { photo -> initDetailBookmarkFragment(photo) }
        binding.bookmarkPics.apply {
            layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
            adapter = this@BookmarkPicsFragment.adapter
        }
    }

    private fun observeViewModel() {
        viewModel.detailPic.observe(viewLifecycleOwner) { images ->
            if (images.isNotEmpty()) {
                adapter.updateList(images)
            } else {
                initEmptyBookmarkPicsFragment()
            }
        }
    }

    private fun initDetailBookmarkFragment(pic: CuratedPicsResponse.Photo) {
        val detailBookmarkFragment = BookmarkDetailFragment.newBookmarkInstance(pic)
        findNavController().navigate(R.id.bookmarkDetailFragment, detailBookmarkFragment.arguments)
    }

    private fun initEmptyBookmarkPicsFragment() {
        Log.d("BookmarkPicsFragment", "Replacing fragment with EmptyBookmarkFragment")
        val emptyBookmarkPicsFragment = EmptyBookmarkFragment()
        parentFragmentManager.beginTransaction()
            .replace(R.id.bookmarkPicsContainer, emptyBookmarkPicsFragment)
            .commit()
    }
}