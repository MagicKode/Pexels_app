package ru.myapp.pexels_app.home.presentation

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import ru.myapp.pexels_app.R
import ru.myapp.pexels_app.category.presentation.CategoryFragment
import ru.myapp.pexels_app.curated.presentation.CuratedPicsFragment
import ru.myapp.pexels_app.databinding.FragmentNoInternetBinding

class NoInternetFragment : Fragment() {

    private lateinit var biding: FragmentNoInternetBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        biding = FragmentNoInternetBinding.inflate(layoutInflater, container, false)
        return biding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        biding.apply {
            picsRequestBtn.setOnClickListener {
                if (isInternetAvailable()) {
                    initCategoryFragment()
                    initCuratedFragment()
                } else {
                    Toast.makeText(context, "No internet connection!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun initCategoryFragment() {
        openFragment(R.id.categoryContainer, CategoryFragment())
    }

    private fun initCuratedFragment() {
        openFragment(R.id.picsContainer, CuratedPicsFragment())
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