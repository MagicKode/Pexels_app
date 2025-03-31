package ru.myapp.pexels_app.bookmarks.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions
import ru.myapp.pexels_app.R
import ru.myapp.pexels_app.databinding.FragmentBookmarkDetailBinding
import ru.myapp.pexels_app.model.CuratedPicsResponse
import ru.myapp.pexels_app.utils.Constant.ARG_IMAGE

class BookmarkDetailFragment : Fragment() {

    private lateinit var binding: FragmentBookmarkDetailBinding
    private lateinit var photo: CuratedPicsResponse.Photo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        photo = arguments?.getParcelable(ARG_IMAGE)!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentBookmarkDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageView: ImageView = view.findViewById(R.id.detailBookmarkImage)
        val photographerNameSurname: TextView = view.findViewById(R.id.photographerTxt)
        Glide.with(this)
            .asBitmap()
            .load(photo.src.original)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .transition(BitmapTransitionOptions.withCrossFade(80))
            .error(R.drawable.placeholder_light)
            .placeholder(R.drawable.placeholder_light)
            .into(imageView)
        photographerNameSurname.text = photo.photographer

        initBackStack()
    }

    private fun initBackStack() {
        binding.apply {
            backBookmarkBtn.setOnClickListener {
                findNavController().navigate(R.id.bookmarkFragment)
            }
        }
    }

    companion object {
        fun newBookmarkInstance(photo: CuratedPicsResponse.Photo): BookmarkDetailFragment {
            val fragment = BookmarkDetailFragment()
            val args = Bundle()
            args.putParcelable(ARG_IMAGE, photo)
            fragment.arguments = args
            return fragment
        }
    }
}
