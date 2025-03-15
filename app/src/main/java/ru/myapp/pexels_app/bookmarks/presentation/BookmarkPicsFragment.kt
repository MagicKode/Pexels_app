package ru.myapp.pexels_app.bookmarks.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
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
import ru.myapp.pexels_app.details.presentation.DetailFragment
import ru.myapp.pexels_app.model.CuratedPicsResponse
import ru.myapp.pexels_app.model.DetailPicResponse
import ru.myapp.pexels_app.viewmodel.DetailViewModel

class BookmarkPicsFragment : Fragment() {

    private lateinit var binding: FragmentBookmarkPicsBinding
    private lateinit var adapter: BookmarkAdapter
    private lateinit var viewModel: DetailViewModel
    private val picsList = mutableListOf<CuratedPicsResponse.Photo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBookmarkPicsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initBookmarkPics()

        adapter = BookmarkAdapter(picsList) { photo ->
            initDetailFragment(photo)
        }
    }

    private fun initBookmarkPics() {
        binding.apply {
            CoroutineScope(Dispatchers.IO).launch {
                val images = viewModel.getAllPics()

                withContext(Dispatchers.Main) {
                    bookmarkPics.layoutManager =
                        StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                }
                adapter = BookmarkAdapter(images) {}
                bookmarkPics.adapter = adapter

                picsList.clear()
                picsList.addAll(images)
                adapter.notifyDataSetChanged()
            }
        }
    }

    private fun initEmptyBookmarkPicsFragment() {
        val emptyBookmarkPicsFragment = EmptyBookmarkFragment()
        childFragmentManager.beginTransaction()
            .replace(R.id.bookmarkPicsContainer, emptyBookmarkPicsFragment)
            .commit()
    }

    private fun initDetailFragment(photo: CuratedPicsResponse.Photo) {
        val detailFragment = DetailFragment.newBookmarkInstance(photo)
        parentFragmentManager.beginTransaction()
            .replace(R.id.bookmarkContainer, detailFragment)
            .commit()
    }
}