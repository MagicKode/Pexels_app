package ru.myapp.pexels_app.details.presentation

import android.content.ContentValues
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.os.Looper
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.util.Executors
import ru.myapp.pexels_app.R
import ru.myapp.pexels_app.databinding.FragmentDetailBinding
import ru.myapp.pexels_app.model.CuratedPicsResponse
import java.io.BufferedInputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

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
                .load(photo.src.original)
                .into(imageView)
            photographerNameSurname.text = it.photographer
        }

        initBackStack()
        initSaveImageToStore()


    }

    private fun initBackStack() {
        binding.apply {
            backBtn.setOnClickListener {
                activity?.supportFragmentManager?.popBackStack()
            }
        }
    }


    // Declaring and initializing an Executor and a Handler
    private fun initSaveImageToStore() {
        val photo = arguments?.getParcelable<CuratedPicsResponse.Photo>(ARG_IMAGE)
        val myExecutor = Executors.directExecutor()
        val myHandler = Handler(Looper.getMainLooper())
        var mImage: Bitmap?

        binding.apply {
            downloadBtn.setOnClickListener {
                myExecutor.execute {
                    mImage = mLoad(photo?.url.toString())
                    myHandler.post {
                        detailImage.setImageBitmap(mImage)
                        if (mImage != null) {
                            initSaveMediaToStorage(mImage)
                        }
                    }
                }
            }
        }
    }

    // Function to establish connection and load image
    private fun mLoad(string: String): Bitmap? {
        val url: URL = mStringToURL(string)!!
        val connection: HttpURLConnection?
        try {
            connection = url.openConnection() as HttpURLConnection
            connection.content
            val inputStream: InputStream = connection.inputStream
            val bufferedReaderInputStream = BufferedInputStream(inputStream)
            return BitmapFactory.decodeStream(bufferedReaderInputStream)
        } catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
        }
        return null
    }

    // Function to convert string to URL
    private fun mStringToURL(string: String): URL? {
        try {
            return URL(string)
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        }
        return null
    }

    //
    private fun initSaveMediaToStorage(bitmap: Bitmap?) {
        val filename = "${System.currentTimeMillis()}.jpg"
        var fos: OutputStream? = null

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            context?.contentResolver.also { resolver ->
                val contentValues = ContentValues().apply {
                    put(MediaStore.MediaColumns.DISPLAY_NAME, filename)
                    put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")
                    put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
                }
                val imageUri: Uri? =
                    resolver?.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
                fos = imageUri?.let { resolver.openOutputStream(it) }
            }
        } else {
            val imagesDir =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
            val image = File(imagesDir, filename)
            fos = FileOutputStream(image)
        }
        fos?.use {
            bitmap?.compress(Bitmap.CompressFormat.JPEG, 100, it)
            Toast.makeText(context, "Saved to Gallery", Toast.LENGTH_SHORT).show()
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

        fun newInstance(photo: CuratedPicsResponse.Photo): DetailFragment {
            val fragment = DetailFragment()
            val args = Bundle()
            args.putParcelable(ARG_IMAGE, photo)
            fragment.arguments = args
            return fragment
        }
    }
}