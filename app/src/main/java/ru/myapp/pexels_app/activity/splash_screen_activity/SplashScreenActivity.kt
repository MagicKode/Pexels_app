package ru.myapp.pexels_app.activity.splash_screen_activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import ru.myapp.pexels_app.activity.MainActivity
import ru.myapp.pexels_app.R
import ru.myapp.pexels_app.activity.BaseActivity

class SplashScreenActivity : BaseActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen_activity)

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this@SplashScreenActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}