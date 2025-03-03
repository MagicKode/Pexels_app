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
import ru.myapp.pexels_app.R
import ru.myapp.pexels_app.adapter.SearchPicsAdapter
import ru.myapp.pexels_app.api.RetrofitClient
import ru.myapp.pexels_app.bookmarks.presentation.BookmarkFragment
import ru.myapp.pexels_app.category.presentation.CategoryFragment
import ru.myapp.pexels_app.databinding.FragmentHomeBinding
import ru.myapp.pexels_app.details.presentation.DetailFragment
import ru.myapp.pexels_app.utils.Constant.API_KEY

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        initCategories()
        initCuratedPics()

//        initNoInternetConnection()

    }

    private fun initCategories() {
        val categoryFragment = CategoryFragment()
        childFragmentManager.beginTransaction()
            .replace(R.id.categoryContainer, categoryFragment)
            .commit()
    }

    private fun initCuratedPics() {
        val curatedFragment = CuratedPicsFragment()
        childFragmentManager.beginTransaction()
            .replace(R.id.picsContainer, curatedFragment)
            .commit()
    }


//    private fun initNoInternetConnection() {
//        val noInternetFragment = NoInternetFragment()
//        childFragmentManager.beginTransaction()
//            .replace(R.id.noInternetContainer, noInternetFragment)
//            .commit()
//    }






//    override fun onItemClick(item: CategoriesResponse.Collection) {
//        binding.apply {
//            searchBarTxt.setText(item.title)
//        }
//    }


//    private fun initProgressBar() {
//        binding.apply {
//            Thread {
//                progressBar.visibility = View.VISIBLE
//                for (i in 0..100) {
//                    Thread.sleep(50)
//                    handler.post {
//                        progressBar.progress = i
//                    }
//                }
////                handler.post {
////                    mainContent.progressBar.visibility = View.GONE
////                }
//            }.start()
//        }
//    }


//    private fun initCategoryClickListener() {
//        binding.apply {
//            searchBarTxt.addTextChangedListener(object : TextWatcher {
//                override fun beforeTextChanged(
//                    s: CharSequence?,
//                    start: Int,
//                    count: Int,
//                    after: Int
//                ) {
//                }
//
//                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                    initSearchPics(s.toString())
//                }
//
//                override fun afterTextChanged(s: Editable?) {
//                    if (s.toString().isNotEmpty()) {
//                        cleanTxtBtn.visibility = View.VISIBLE
//                        initSearchPics(s.toString())
////                        initProgressBar()
//                    } else {
//                        cleanTxtBtn.visibility = View.GONE
//                        initCuratedPics()
//                    }
//                }
//            })
//            cleanTxtBtn.setOnClickListener {
//                searchBarTxt.text?.clear()
//            }
//        }
//    }


//    private fun initSearchPics(query: String) {
//        binding.apply {
//            progressBar.visibility = View.VISIBLE
//
//            CoroutineScope(Dispatchers.IO).launch {
//                val response = RetrofitClient.instance.searchPics(query, 1, 30, API_KEY)
//                withContext(Dispatchers.Main) {
//                    viewPictures.layoutManager = GridLayoutManager(context, 2)
//                    viewPictures.adapter = SearchPicsAdapter(response.photos)
//                }
//            }
//        }
//    }
}