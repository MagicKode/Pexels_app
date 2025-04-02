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
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import ru.myapp.pexels_app.R
import ru.myapp.pexels_app.databinding.FragmentDetailBinding
import ru.myapp.pexels_app.db.PexelsDatabase
import ru.myapp.pexels_app.db.repository.impl.DetailCuratedPicsRepositoryImpl
import ru.myapp.pexels_app.model.CuratedPicsResponse
import ru.myapp.pexels_app.utils.Constant.ARG_IMAGE
import ru.myapp.pexels_app.viewmodel.DetailCuratedPicsViewModel
import ru.myapp.pexels_app.viewmodel.viewmodelfactory.DetailCuratedViewModelFactory

class DetailCuratedPicsFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private lateinit var detailCuratedPicsViewModel: DetailCuratedPicsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imageView: ImageView = view.findViewById(R.id.detailImage)
        val photographerNameSurname: TextView = view.findViewById(R.id.nameSurnameTxt)
        val photo = arguments?.getParcelable<CuratedPicsResponse.Photo>(ARG_IMAGE)
        photo?.let {
            Glide.with(this)
                .load(photo.src.original)
                .skipMemoryCache(false)
                .centerCrop()
                .error(R.drawable.placeholder_light)
                .placeholder(R.drawable.placeholder_light)
                .into(imageView)
            photographerNameSurname.text = it.photographer
        }
        initBackStack()

        //download image to local phone gallery
        initDownloadImageToPhone()

        if (photo != null) {
            saveImageInDb(photo)
        }
    }

    private fun setupViewModel() {
        val picDao = PexelsDatabase.getDatabase(requireContext()).getPicDao()
        val picsRepository = DetailCuratedPicsRepositoryImpl(picDao)
        val factory = DetailCuratedViewModelFactory(picsRepository)
        detailCuratedPicsViewModel =
            ViewModelProvider(this, factory).get(DetailCuratedPicsViewModel::class.java)
    }

    private fun initBackStack() {
        binding.apply {
            backBtn.setOnClickListener {
                findNavController().navigate(R.id.homeFragment)
            }
        }
    }

    private fun initDownloadImage() {
        val photo = arguments?.getParcelable<CuratedPicsResponse.Photo>(ARG_IMAGE)

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

    private fun initDownloadImageToPhone() {
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
    }

    private fun saveImageInDb(photo: CuratedPicsResponse.Photo) {
        binding.bookmarkBtn.setOnClickListener {
            detailCuratedPicsViewModel.insertPic(photo)
            Toast.makeText(context, "Image saved", Toast.LENGTH_SHORT).show()
        }
    }

    //permission for save image in gallery
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

        fun newInstance(photo: CuratedPicsResponse.Photo): DetailCuratedPicsFragment {
            val fragment = DetailCuratedPicsFragment()
            val args = Bundle()
            args.putParcelable(ARG_IMAGE, photo)
            fragment.arguments = args
            return fragment
        }
    }
}