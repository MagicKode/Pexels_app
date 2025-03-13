package ru.myapp.pexels_app

import android.app.Application
import androidx.room.Room
import ru.myapp.pexels_app.db.PexelsDatabase

class App: Application() {

    lateinit var db: PexelsDatabase

    override fun onCreate() {
        super.onCreate()

        db = Room.databaseBuilder(
            applicationContext,
            PexelsDatabase::class.java,
            "pexels_database"
        ).build()
    }
}
