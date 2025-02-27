package ru.myapp.pexels_app.parent.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import ru.myapp.pexels_app.R
import ru.myapp.pexels_app.adapter.PagerAdapter
import ru.myapp.pexels_app.databinding.FragmentParentBinding

class ParentFragment : Fragment() {

    private lateinit var binding: FragmentParentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentParentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initFragmentSelector()
    }

    private fun initFragmentSelector() {
        binding.apply {
            viewPager.adapter = PagerAdapter(this@ParentFragment)

            navigation.setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.action_home -> {
                        viewPager.currentItem = 0
                        true
                    }

                    R.id.action_bookmark -> {
                        viewPager.currentItem = 1
                        true
                    }

                    else -> false
                }
            }

            viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    navigation.menu.getItem(position).isChecked = true
                }
            })
        }
    }
}