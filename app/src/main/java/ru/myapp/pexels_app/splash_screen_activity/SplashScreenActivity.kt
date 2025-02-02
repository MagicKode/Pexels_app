package ru.myapp.pexels_app.splash_screen_activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.myapp.pexels_app.R
import ru.myapp.pexels_app.activity.BaseActivity

class SplashScreenActivity : BaseActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen_activity)
    }
}