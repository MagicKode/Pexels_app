package ru.myapp.pexels_app.home.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.myapp.pexels_app.R
import ru.myapp.pexels_app.adapter.CuratedAdapter
import ru.myapp.pexels_app.api.RetrofitClient
import ru.myapp.pexels_app.databinding.FragmentCuratedBinding
import ru.myapp.pexels_app.details.presentation.DetailFragment
import ru.myapp.pexels_app.model.CuratedPicsResponse
import ru.myapp.pexels_app.utils.Constant.API_KEY

class CuratedPicsFragment : Fragment() {

    private lateinit var binding: FragmentCuratedBinding
    private lateinit var adapter: CuratedAdapter
    private val picsList = mutableListOf<CuratedPicsResponse.Photo>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentCuratedBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initCuratedPics()

        adapter = CuratedAdapter(picsList) { photo ->
            initDetailFragment(photo)
        }
    }

    private fun initCuratedPics() {
        binding.apply {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val response = RetrofitClient.instance.getCuratedPicList(1, 30, API_KEY)
                    withContext(Dispatchers.Main) {
                        viewPictures.layoutManager =
                            StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                        viewPictures.adapter = adapter

                        picsList.clear()
                        picsList.addAll(response.photos)
                        adapter.notifyDataSetChanged()
                    }
                } catch (e: Exception) {
                    Log.e("CuratedPicsFragment", "Exception: ${e.message}")
                }
            }
        }
    }

    private fun initDetailFragment(photo: CuratedPicsResponse.Photo) {
        val detailFragment = DetailFragment.newInstance(photo)
        parentFragmentManager.beginTransaction()
            .replace(R.id.detailContainer, detailFragment)
            .commit()
    }
}