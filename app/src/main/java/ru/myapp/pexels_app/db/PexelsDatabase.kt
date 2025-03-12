package ru.myapp.pexels_app.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.myapp.pexels_app.db.dao.PicDao
import ru.myapp.pexels_app.model.CuratedPicsResponse
import ru.myapp.pexels_app.model.DetailPicResponse

@Database(
    entities = [
        DetailPicResponse::class,
        CuratedPicsResponse.Photo::class
    ], version = 1
)
abstract class PexelsDatabase : RoomDatabase() {
    abstract fun getPicDao(): PicDao

    companion object {
        @Volatile
        private var database: PexelsDatabase? = null

        fun getDatabase(context: Context): PexelsDatabase {
            return database ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PexelsDatabase::class.java,
                    "pexels_database"
                ).build()
                database = instance
                instance
            }
        }
    }
}