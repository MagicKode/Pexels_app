package ru.myapp.pexels_app.home.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.myapp.pexels_app.adapter.CuratedAdapter
import ru.myapp.pexels_app.adapter.listener.OnImageClickListener
import ru.myapp.pexels_app.api.RetrofitClient
import ru.myapp.pexels_app.databinding.FragmentCuratedBinding
import ru.myapp.pexels_app.model.DetailPicResponse
import ru.myapp.pexels_app.utils.Constant.API_KEY

class CuratedPicsFragment : Fragment(), OnImageClickListener {

    private lateinit var binding: FragmentCuratedBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCuratedBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCuratedPics()

    }

    private fun initCuratedPics() {
        binding.apply {
            CoroutineScope(Dispatchers.IO).launch {
                val response = RetrofitClient.instance.getCuratedPicList(1, 30, API_KEY)
                withContext(Dispatchers.Main) {
                    viewPictures.layoutManager = GridLayoutManager(context, 2)
                    viewPictures.adapter = CuratedAdapter(response.photos, this@CuratedPicsFragment)
                }
            }
        }
    }

    override fun onImageClick(pic: DetailPicResponse) {
        //TODO show DetailFragment
    }
}