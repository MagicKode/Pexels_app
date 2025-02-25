package ru.myapp.pexels_app.home.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.myapp.pexels_app.databinding.FragmentNoInternetBinding

class NoInternetFragment: Fragment() {

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

        initSearchPics()
    }

    private fun initSearchPics() {
        biding.apply {
            picsRequestBtn.setOnClickListener{
                //TODo request to server PEXELS for loading categories/pics
            }
        }
    }
}