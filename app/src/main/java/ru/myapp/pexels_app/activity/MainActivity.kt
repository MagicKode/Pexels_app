package ru.myapp.pexels_app.activity

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.myapp.pexels_app.adapter.CuratedAdapter
import ru.myapp.pexels_app.adapter.FeaturedCollectionsAdapter
import ru.myapp.pexels_app.adapter.SearchPicsAdapter
import ru.myapp.pexels_app.api.RetrofitClient
import ru.myapp.pexels_app.databinding.ActivityMainBinding
import ru.myapp.pexels_app.model.CuratedPicsResponse
import ru.myapp.pexels_app.model.FeaturedCollectionsResponse
import ru.myapp.pexels_app.model.SearchPicsResponse
import ru.myapp.pexels_app.utils.Constant.API_KEY

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initCategories()
        initCuratedPics()

        binding.apply {
            mainContent.searchBarTxt.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                    initSearchPics(s.toString())
                }

                override fun afterTextChanged(s: Editable?) {
                    if (s.toString().isNotEmpty()) {
                        mainContent.cleanTxtBtn.visibility = View.VISIBLE
                        initSearchPics(s.toString())
                    } else {
                        mainContent.cleanTxtBtn.visibility = View.GONE
                        initCuratedPics()
                    }
                }
            })
            mainContent.cleanTxtBtn.setOnClickListener {
                mainContent.searchBarTxt.text?.clear()
            }
        }
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
                        if (response.isSuccessful) {
                            response.body()?.collections.let {
                                mainContent.viewCategory.layoutManager =
                                    LinearLayoutManager(
                                        this@MainActivity,
                                        LinearLayoutManager.HORIZONTAL,
                                        false
                                    )
                                mainContent.viewCategory.adapter =
                                    FeaturedCollectionsAdapter(it as List<FeaturedCollectionsResponse.Collection>)
                            }
                        }
                    }

                    override fun onFailure(p0: Call<FeaturedCollectionsResponse>, p1: Throwable) {
                        Toast.makeText(
                            this@MainActivity,
                            "Ошибка: ${p1.message}",
                            Toast.LENGTH_LONG
                        )
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
                                mainContent.viewPictures.layoutManager =
                                    GridLayoutManager(this@MainActivity, 2)
                                mainContent.viewPictures.adapter =
                                    CuratedAdapter(it as List<CuratedPicsResponse.Photo>)
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

            RetrofitClient.instance.searchPics(query, 1, 30, API_KEY)
                .enqueue(object : Callback<SearchPicsResponse> {
                    override fun onResponse(
                        call: Call<SearchPicsResponse>,
                        response: Response<SearchPicsResponse>
                    ) {
                        if (response.isSuccessful) {
                            response.body()?.photos?.let {
                                mainContent.viewPictures.layoutManager =
                                    GridLayoutManager(this@MainActivity, 2)
                                mainContent.viewPictures.adapter =
                                    SearchPicsAdapter(it as List<SearchPicsResponse.Photo>)
                            }
                        }
                    }

                    override fun onFailure(call: Call<SearchPicsResponse>, t: Throwable) {
                        Toast.makeText(this@MainActivity, "Ошибка: ${t.message}", Toast.LENGTH_LONG)
                            .show()
                    }
                })
        }
    }
}
