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
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions
import ru.myapp.pexels_app.R
import ru.myapp.pexels_app.databinding.FragmentDetailBinding
import ru.myapp.pexels_app.model.CuratedPicsResponse

class DetailFragment : Fragment(R.layout.fragment_detail) {

    private lateinit var binding: FragmentDetailBinding

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
                .asBitmap()
                .load(photo.src.original)
                .centerCrop()
                .transition(BitmapTransitionOptions.withCrossFade(80))
                .error(R.drawable.placeholder_light)
                .placeholder(R.drawable.placeholder_light)
                .into(imageView)
            photographerNameSurname.text = it.photographer
        }

        initBackStack()

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

        binding.apply {
            bookmarkBtn.setOnClickListener{
                //
            }
        }
    }

    private fun initBackStack() {
        binding.apply {
            backBtn.setOnClickListener {
                activity?.supportFragmentManager?.popBackStack()
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


    private fun initBookmarkImage() {
        binding.apply {
            downloadBtn.setOnClickListener {
                // TODO download image functional
            }
        }
    }

    companion object {
        private const val ARG_IMAGE = "photo"
        private val STORAGE_PERMISSION_CODE = 100

        fun newInstance(photo: CuratedPicsResponse.Photo): DetailFragment {
            val fragment = DetailFragment()
            val args = Bundle()
            args.putParcelable(ARG_IMAGE, photo)
            fragment.arguments = args
            return fragment
        }
    }
}