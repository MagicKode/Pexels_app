package ru.myapp.pexels_app.details.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import ru.myapp.pexels_app.R
import ru.myapp.pexels_app.databinding.FragmentDetailBinding
import ru.myapp.pexels_app.model.DetailPicResponse
import ru.myapp.pexels_app.utils.Constant.MAIN

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private lateinit var photo: DetailPicResponse

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageView: ImageView = view.findViewById(R.id.detailImage)
        val photographerNameSurname: TextView = view.findViewById(R.id.nameSurnameTxt)

//        initImageFragment()

//        Glide.with(this)
//            .load(photo.src?.original)
//            .into(imageView)
//        photographerNameSurname.text = photo.photographer
    }

    private fun initImageFragment() {
        val imageFragment = ImageFragment()
        childFragmentManager.beginTransaction()
            .replace(R.id.imageContainer, imageFragment)
            .commit()
    }
}