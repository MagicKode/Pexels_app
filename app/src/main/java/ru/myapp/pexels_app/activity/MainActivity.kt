package ru.myapp.pexels_app.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.myapp.pexels_app.adapter.FeaturedCollectionsAdapter
import ru.myapp.pexels_app.adapter.CuratedAdapter
import ru.myapp.pexels_app.adapter.PicListAdapter
import ru.myapp.pexels_app.api.RetrofitClient
import ru.myapp.pexels_app.databinding.ActivityMainBinding
import ru.myapp.pexels_app.model.CuratedPicsResponse
import ru.myapp.pexels_app.model.FeaturedCollectionsResponse
import ru.myapp.pexels_app.model.PexelsResponse

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var featuredCollectionsAdapter: FeaturedCollectionsAdapter
    private lateinit var curatedPicAdapter: CuratedAdapter
    private lateinit var picAdapter: PicListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initCategories()
        initCuratedPics()
//        initSearchPics("cars")
    }

    private fun initCategories() {
        binding.apply {
            mainContent.progressBar.visibility = View.VISIBLE

            RetrofitClient.instance.getFeaturedCollections(1, 7)
                .enqueue(object : Callback<FeaturedCollectionsResponse> {
                    override fun onResponse(
                        call: Call<FeaturedCollectionsResponse>,
                        response: Response<FeaturedCollectionsResponse>
                    ) {
                        if(response.isSuccessful) {
                            response.body()?.collections.let {
                                featuredCollectionsAdapter = FeaturedCollectionsAdapter(it as List<FeaturedCollectionsResponse.Collection>)
                                mainContent.viewCategory.adapter = featuredCollectionsAdapter
                                mainContent.viewCategory.layoutManager =
                                    LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
                            }
                        }
                    }

                    override fun onFailure(p0: Call<FeaturedCollectionsResponse>, p1: Throwable) {
                        Toast.makeText(this@MainActivity, "Ошибка: ${p1.message}", Toast.LENGTH_LONG)
                            .show()
                    }
                })
            mainContent.progressBar.visibility = View.GONE
        }
    }

    private fun initCuratedPics() {
        binding.apply {
            mainContent.progressBar.visibility = View.VISIBLE

            RetrofitClient.instance.getCuratedPicList(1, 30)
                .enqueue(object : Callback<CuratedPicsResponse> {
                    override fun onResponse(
                        call: Call<CuratedPicsResponse>,
                        response: Response<CuratedPicsResponse>
                    ) {
                        if (response.isSuccessful) {
                            response.body()?.photos?.let {
                                curatedPicAdapter = CuratedAdapter(it as List<CuratedPicsResponse.Photo>)
                                mainContent.viewPictures.adapter = curatedPicAdapter
                                mainContent.viewPictures.layoutManager = GridLayoutManager(this@MainActivity, 2)
                            }
                        }
                    }

                    override fun onFailure(call: Call<CuratedPicsResponse>, t: Throwable) {
                        Toast.makeText(this@MainActivity, "Ошибка: ${t.message}", Toast.LENGTH_LONG)
                            .show()
                    }
                })
            mainContent.progressBar.visibility = View.GONE
        }
    }


    private fun initSearchPics(query: String) {
        binding.apply {
            mainContent.progressBar.visibility = View.VISIBLE

            RetrofitClient.instance.getPicList(query, 1, 30)
                .enqueue(object : Callback<PexelsResponse> {
                    override fun onResponse(
                        call: Call<PexelsResponse>,
                        response: Response<PexelsResponse>
                    ) {
                        if (response.isSuccessful) {
                            response.body()?.photos?.let {
                                picAdapter = PicListAdapter(it as List<PexelsResponse.Photo>)
                                mainContent.viewPictures.adapter = picAdapter
                                mainContent.viewPictures.layoutManager = GridLayoutManager(this@MainActivity, 2)
                            }
                        }
                    }

                    override fun onFailure(call: Call<PexelsResponse>, t: Throwable) {
                        Toast.makeText(this@MainActivity, "Ошибка: ${t.message}", Toast.LENGTH_LONG)
                            .show()
                    }
                })

            mainContent.progressBar.visibility = View.GONE
        }
    }


}