package ru.myapp.pexels_app.home.presentation

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
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

        binding.apply {
            initSearchFragment()
            progressBar.visibility = View.VISIBLE
            if (isInternetAvailable()) {

                initCategoryFragment()
                initCuratedFragment()
                progressBar.visibility = View.GONE
            } else {
                initNoInternetFragment()
            }
        }
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

    private fun initNoInternetFragment() {
        openFragment(R.id.noInternetContainer, NoInternetFragment())
    }

    private fun openFragment(idHolder: Int, f: Fragment) {
        childFragmentManager.beginTransaction()
            .replace(idHolder, f)
            .commit()
    }

    private fun isInternetAvailable(): Boolean {
        val connectivityManager =
            context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = connectivityManager.activeNetwork?.let {
                connectivityManager.getNetworkCapabilities(it)
            }
            return networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
        } else {
            val networkInfo = connectivityManager.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnected
        }
    }

}




