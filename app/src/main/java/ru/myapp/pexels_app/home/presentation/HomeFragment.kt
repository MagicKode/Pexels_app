package ru.myapp.pexels_app.home.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.myapp.pexels_app.R
import ru.myapp.pexels_app.search.presentation.SearchFragment
import ru.myapp.pexels_app.category.presentation.CategoryFragment
import ru.myapp.pexels_app.curated.presentation.CuratedPicsFragment
import ru.myapp.pexels_app.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initSearchFragment()
        initCategoryFragment()
        initCuratedFragment()

    }

    private fun initCategoryFragment() {
        openFragment(R.id.categoryContainer, CategoryFragment())
    }

    private fun initCuratedFragment() {
        openFragment(R.id.picsContainer, CuratedPicsFragment())
    }

    private fun initSearchFragment() {
        openFragment(R.id.searchBarContainer, SearchFragment())
    }

    private fun openFragment(idHolder: Int, f: Fragment) {
        childFragmentManager.beginTransaction()
            .replace(idHolder, f)
            .commit()
    }
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