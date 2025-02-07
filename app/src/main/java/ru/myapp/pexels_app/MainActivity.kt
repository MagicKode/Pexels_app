package ru.myapp.pexels_app

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import ru.myapp.pexels_app.activity.BaseActivity
import ru.myapp.pexels_app.adapter.CategoryAdapter
import ru.myapp.pexels_app.adapter.PicListAdapter
import ru.myapp.pexels_app.databinding.ActivityMainBinding
import ru.myapp.pexels_app.databinding.ContentMainBinding

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
//    private lateinit var mainContentBinding: ContentMainBinding
    
    private lateinit var adapter: CategoryAdapter
    private lateinit var picAdapter: PicListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initCategories()
//        initItems()



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
            adapter = CategoryAdapter(categories)
            mainContent.progressBar.visibility = View.VISIBLE

            mainContent.viewCategory.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            mainContent.viewCategory.adapter = adapter
            mainContent.progressBar.visibility = View.GONE
        }
    }

//    private fun initItems() {
//
//        mainContentBinding.apply {
//            picAdapter = PicListAdapter()
//            progressBar.visibility = View.VISIBLE
//
//            viewPictures.layoutManager = GridLayoutManager(this@MainActivity, 2)
//            viewPictures.adapter = picAdapter
//            progressBar.visibility = View.GONE
//        }
//    }
}