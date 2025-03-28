package ru.myapp.pexels_app.details.presentation

import android.app.Activity
import android.app.DownloadManager
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import ru.myapp.pexels_app.R
import ru.myapp.pexels_app.databinding.FragmentDetailSearchPicsBinding
import ru.myapp.pexels_app.db.PexelsDatabase
import ru.myapp.pexels_app.db.repository.impl.DetailSearchPicsRepositoryImpl
import ru.myapp.pexels_app.model.SearchPicsResponse
import ru.myapp.pexels_app.utils.Constant.ARG_IMAGE
import ru.myapp.pexels_app.viewmodel.DetailSearchPicsViewModel
import ru.myapp.pexels_app.viewmodel.viewmodelfactory.DetailSearchViewModelFactory

class DetailSearchPicsFragment : Fragment() {
    private lateinit var binding: FragmentDetailSearchPicsBinding
    private lateinit var detailSearchPicsViewModel: DetailSearchPicsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val picDao = PexelsDatabase.getDatabase(requireContext()).getPicDao()
        val picsRepository = DetailSearchPicsRepositoryImpl(picDao)
        val factory = DetailSearchViewModelFactory(picsRepository)
        detailSearchPicsViewModel = ViewModelProvider(this, factory).get(DetailSearchPicsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailSearchPicsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imageView: ImageView = view.findViewById(R.id.detailImage)
        val photographerNameSurname: TextView = view.findViewById(R.id.nameSurnameTxt)
        val photo = arguments?.getParcelable<SearchPicsResponse.Photo>(ARG_IMAGE)
        photo?.let {
            Glide.with(this)
                .load(photo.src.original)
                .centerCrop()
                .error(R.drawable.placeholder_light)
                .placeholder(R.drawable.placeholder_light)
                .into(imageView)
            photographerNameSurname.text = it.photographer
        }
        initBackStack()

        //download image to local phone gallery
        binding.apply {
            downloadBtn.setOnClickListener {
                if (ContextCompat.checkSelfPermission(
                        requireContext(),
                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    initDownloadImage()
                    Toast.makeText(context, "Downloaded successful", Toast.LENGTH_LONG).show()
                } else {
                    ActivityCompat.requestPermissions(
                        context as Activity,
                        arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),
                        STORAGE_PERMISSION_CODE
                    )
                }
            }
        }

        if (photo != null) {
            saveImageInDb(photo)
        }
    }

    private fun initBackStack() {
        binding.apply {
            backBtn.setOnClickListener {
                activity?.supportFragmentManager?.popBackStackImmediate()
            }
        }
    }

    private fun initDownloadImage() {
        val photo = arguments?.getParcelable<SearchPicsResponse.Photo>(ARG_IMAGE)

        binding.apply {
            val url = photo?.url
            val downloadManager =
                requireContext().getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            val uri = Uri.parse(url)
            val request = DownloadManager.Request(uri)
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "image.jpg")
            downloadManager.enqueue(request)
        }
    }

    private fun saveImageInDb(photo: SearchPicsResponse.Photo) {
        binding.bookmarkBtn.setOnClickListener {
            detailSearchPicsViewModel.insertPic(photo)
            Toast.makeText(context, "Image saved", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == STORAGE_PERMISSION_CODE && grantResults.isNotEmpty() &&
            grantResults[0] == PackageManager.PERMISSION_GRANTED
        ) {
            initDownloadImage()
        }
    }

    companion object {
        private val STORAGE_PERMISSION_CODE = 100

        fun newSearchInstance(photo: SearchPicsResponse.Photo): DetailSearchPicsFragment {
            val fragment = DetailSearchPicsFragment()
            val args = Bundle()
            args.putParcelable(ARG_IMAGE, photo)
            fragment.arguments = args
            return fragment
        }
    }
}
