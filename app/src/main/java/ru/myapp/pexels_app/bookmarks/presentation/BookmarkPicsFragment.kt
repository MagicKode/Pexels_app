package ru.myapp.pexels_app.bookmarks.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.myapp.pexels_app.R
import ru.myapp.pexels_app.adapter.BookmarkAdapter
import ru.myapp.pexels_app.databinding.FragmentBookmarkPicsBinding
import ru.myapp.pexels_app.db.PexelsDatabase
import ru.myapp.pexels_app.details.presentation.DetailFragment
import ru.myapp.pexels_app.model.DetailPicResponse
import ru.myapp.pexels_app.viewmodel.DetailViewModel

class BookmarkPicsFragment : Fragment() {

    private lateinit var binding: FragmentBookmarkPicsBinding
    private lateinit var adapter: BookmarkAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBookmarkPicsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initBookmarkPicsFragment()
        init()

//        initBookmarkPics()
    }

    private fun init() {
        val viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        recyclerView = binding.bookmarkPics
        adapter = BookmarkAdapter()
        recyclerView.adapter = adapter
//        viewModel.getAllPics().observe(viewLifecycleOwner, { pics ->
//            adapter.setImages(pics.asReversed())
//        })
    }

    private fun initBookmarkPicsFragment() {
        val bookmarkPicsFragment = BookmarkPicsFragment()
        childFragmentManager.beginTransaction()
            .replace(R.id.bookmarkPicsContainer, bookmarkPicsFragment)
            .commit()
    }

    private fun initEmptyBookmarkPicsFragment() {
        val emptyBookmarkPicsFragment = EmptyBookmarkFragment()
        childFragmentManager.beginTransaction()
            .replace(R.id.bookmarkPicsContainer, emptyBookmarkPicsFragment)
            .commit()
    }

    private fun initDetailFragment(photo: DetailPicResponse) {
        val detailFragment = DetailFragment.newBookmarkInstance(photo)
        parentFragmentManager.beginTransaction()
            .replace(R.id.bookmarkPicsContainer, detailFragment)
            .addToBackStack(null)
            .commit()
    }
}