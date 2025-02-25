package ru.myapp.pexels_app.parent.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.myapp.pexels_app.R
import ru.myapp.pexels_app.bookmarks.presentation.BookmarkFragment
import ru.myapp.pexels_app.databinding.FragmentParentBinding
import ru.myapp.pexels_app.home.presentation.HomeFragment
import ru.myapp.pexels_app.home.presentation.NoInternetFragment

class ParentFragment : Fragment() {

    private lateinit var binding: FragmentParentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentParentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initHomeFragment()
//                initBookmarkFragment()
    }

    private fun initHomeFragment() {
        val homeFragment = HomeFragment()
        childFragmentManager.beginTransaction()
            .replace(R.id.parentContainer, homeFragment)
            .commit()
    }

    private fun initBookmarkFragment() {
        val bookmarkFragment = BookmarkFragment()
        childFragmentManager.beginTransaction()
            .replace(R.id.parentContainer, bookmarkFragment)
            .commit()
    }
}