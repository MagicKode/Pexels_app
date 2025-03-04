package ru.myapp.pexels_app.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.myapp.pexels_app.db.dao.PicDao
import ru.myapp.pexels_app.model.DetailPicResponse

@Database(
    entities = [
        DetailPicResponse::class
    ],
    version = 1, //todo version up after changing database structure
    exportSchema = false
)

abstract class AppDatabaseHelper : RoomDatabase() {
    abstract fun picDao(): PicDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabaseHelper? = null

        fun getDatabase(context: Context): AppDatabaseHelper {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabaseHelper::class.java,
                    "pexels_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}