package ru.myapp.pexels_app.bookmarks.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.myapp.pexels_app.R
import ru.myapp.pexels_app.databinding.FragmentBookmarkBinding
import ru.myapp.pexels_app.databinding.FragmentBookmarkPicsBinding

class BookmarkFragment : Fragment() {

    private lateinit var binding: FragmentBookmarkBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBookmarkBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bookmarkPicsFragment = BookmarkPicsFragment()

        childFragmentManager.beginTransaction()
            .replace(R.id.bookmarkPicsContainer, bookmarkPicsFragment)
    }
}