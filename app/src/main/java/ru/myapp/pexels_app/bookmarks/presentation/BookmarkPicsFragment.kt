package ru.myapp.pexels_app.bookmarks.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.myapp.pexels_app.R
import ru.myapp.pexels_app.adapter.BookmarkAdapter
import ru.myapp.pexels_app.databinding.FragmentBookmarkPicsBinding
import ru.myapp.pexels_app.db.PexelsDatabase
import ru.myapp.pexels_app.db.repository.PicsRepositoryImpl
import ru.myapp.pexels_app.model.CuratedPicsResponse
import ru.myapp.pexels_app.viewmodel.DetailViewModel
import ru.myapp.pexels_app.viewmodel.DetailViewModelFactory

class BookmarkPicsFragment : Fragment(), BookmarkAdapter.OnImageClickListener {

    private lateinit var binding: FragmentBookmarkPicsBinding
    private lateinit var adapter: BookmarkAdapter
    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val picDao = PexelsDatabase.getDatabase(requireContext()).getPicDao()
        val picsRepository = PicsRepositoryImpl(picDao)
        val factory = DetailViewModelFactory(picsRepository)
        viewModel = ViewModelProvider(this, factory).get(DetailViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBookmarkPicsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView: RecyclerView = view.findViewById(R.id.bookmarkPics)
        adapter = BookmarkAdapter(mutableListOf(), this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)

        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        viewModel.getAllPics()
        viewModel.detailPic.observe(viewLifecycleOwner, { images ->
            adapter = BookmarkAdapter(images, this)
            recyclerView.adapter = adapter
        })
    }

    private fun initEmptyBookmarkPicsFragment() {
        val emptyBookmarkPicsFragment = EmptyBookmarkFragment()
        childFragmentManager.beginTransaction()
            .replace(R.id.bookmarkPicsContainer, emptyBookmarkPicsFragment)
            .commit()
    }

    override fun onImageClick(pic: CuratedPicsResponse.Photo) {
        val detailBookmarkFragment = BookmarkDetailFragment.newBookmarkInstance(pic)
        findNavController().navigate(R.id.bookmarkDetailFragment, detailBookmarkFragment.arguments)
    }
}