package ru.myapp.pexels_app.main

import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import ru.myapp.pexels_app.R
import ru.myapp.pexels_app.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showParentFragment()
    }

    private fun showParentFragment() {
        binding.apply {
            val nav = Navigation.findNavController(this@MainActivity, R.id.navHostFragment)
            navigation.setupWithNavController(nav)
        }
    }
}
