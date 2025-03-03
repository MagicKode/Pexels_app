package ru.myapp.pexels_app.main

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import ru.myapp.pexels_app.R
import ru.myapp.pexels_app.databinding.ActivityMainBinding
import ru.myapp.pexels_app.utils.Constant.MAIN

class MainActivity : BaseActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController

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
