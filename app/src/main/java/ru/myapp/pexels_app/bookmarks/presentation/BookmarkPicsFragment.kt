package ru.myapp.pexels_app.bookmarks.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.myapp.pexels_app.R
import ru.myapp.pexels_app.databinding.FragmentBookmarkPicsBinding

class BookmarkPicsFragment : Fragment() {

    private lateinit var binding : FragmentBookmarkPicsBinding

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
    }

    private fun initBookmarkPicsFragment() {
        val  bookmarkPicsFragment = BookmarkPicsFragment()

        childFragmentManager.beginTransaction()
            .replace(R.id.bookmarkPicsContainer, bookmarkPicsFragment)
            .commit()
    }
}