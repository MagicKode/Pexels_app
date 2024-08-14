package com.example.pexels_app.view.main

import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.pexels_app.R
import com.example.pexels_app.bookmark.BookmarkFragment
import com.example.pexels_app.databinding.ActivityMainBinding
import com.example.pexels_app.view.home.HomeFragment

class MainActivity : AppCompatActivity() {

    private lateinit var searchView: SearchView
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(findViewById(R.id.main_toolbar))

        binding.mainContent.navigation.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.home -> {
                    loadFragment(HomeFragment())
                    true
                }
                R.id.bookmark -> {
                    loadFragment(BookmarkFragment())
                    true
                }
                else -> { false}
            }
        }
    }

    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container,fragment)
        transaction.commit()
    }
}
