package ru.myapp.pexels_app.bookmarks.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.myapp.pexels_app.adapter.BookmarkAdapter
import ru.myapp.pexels_app.databinding.FragmentEmptyBookmarkBinding
import ru.myapp.pexels_app.db.PexelsDatabase
import ru.myapp.pexels_app.db.repository.impl.BookmarkRepositoryImpl
import ru.myapp.pexels_app.model.CuratedPicsResponse
import ru.myapp.pexels_app.viewmodel.BookmarkViewModel
import ru.myapp.pexels_app.viewmodel.viewmodelfactory.BookmarkViewModelFactory

class EmptyBookmarkFragment : Fragment() {

    private lateinit var binding: FragmentEmptyBookmarkBinding
    private lateinit var viewModel: BookmarkViewModel
    private lateinit var adapter: BookmarkAdapter
    private val picsList = mutableListOf<CuratedPicsResponse.Photo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEmptyBookmarkBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()
        initRepeatImageRequest()
    }

    private fun setupViewModel() {
        val picDao = PexelsDatabase.getDatabase(requireContext()).getPicDao()
        val picsRepository = BookmarkRepositoryImpl(picDao)
        val factory = BookmarkViewModelFactory(picsRepository)
        viewModel = ViewModelProvider(this, factory).get(BookmarkViewModel::class.java)
    }

    private fun initRepeatImageRequest() {
        binding.apply {
            exploreBtn.setOnClickListener {
                viewModel.getAllPics()
                viewModel.detailPic
            }
        }
    }

    private fun observeViewModel() {
        viewModel.detailPic.observe(viewLifecycleOwner) { pic ->
            adapter = BookmarkAdapter(picsList) {
                adapter.updateList(pic)
            }
        }
    }

}
