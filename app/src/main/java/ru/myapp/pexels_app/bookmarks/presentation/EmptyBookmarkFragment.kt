package ru.myapp.pexels_app.bookmarks.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.myapp.pexels_app.databinding.FragmentEmptyBookmarkBinding

class EmptyBookmarkFragment : Fragment() {

    private lateinit var binding: FragmentEmptyBookmarkBinding

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

        initRepeatImageRequest()

    }

    private fun initRepeatImageRequest() {
        binding.apply {
            exploreBtn.setOnClickListener{
                //TODO request in DB for images
            }
        }
    }
}
