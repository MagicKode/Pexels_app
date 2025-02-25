package ru.myapp.pexels_app.details.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.myapp.pexels_app.databinding.FragmentNoImageFoundBinding

class NoImageFoundFragment: Fragment() {

    private lateinit var binding: FragmentNoImageFoundBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNoImageFoundBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    private fun initRequestImageFromServer() {
        binding.exploreImageBtn.setOnClickListener{
            // TODO request to load image from PEXELS server
        }
    }
}