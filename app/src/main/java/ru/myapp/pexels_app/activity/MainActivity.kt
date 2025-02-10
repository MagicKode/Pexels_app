package ru.myapp.pexels_app.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.myapp.pexels_app.adapter.CategoryAdapter
import ru.myapp.pexels_app.adapter.PicListAdapter
import ru.myapp.pexels_app.api.RetrofitClient
import ru.myapp.pexels_app.databinding.ActivityMainBinding
import ru.myapp.pexels_app.model.PexelsResponse

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var picAdapter: PicListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initCategories()
        initPics("nature")
    }

    private fun initCategories() {

        val categories = listOf(
            "Item 1",
            "Category 2",
            "Item 3",
            " 4",
            "Item 5",
            "Item 6",
            "Item 7"
        )

        binding.apply {
            categoryAdapter = CategoryAdapter(categories)


            mainContent.viewCategory.layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            mainContent.viewCategory.adapter = categoryAdapter
            mainContent.progressBar.visibility = View.GONE
        }
    }

    private fun initPics(query: String) {
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