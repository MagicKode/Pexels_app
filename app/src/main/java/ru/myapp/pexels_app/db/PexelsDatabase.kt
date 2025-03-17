package ru.myapp.pexels_app.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import ru.myapp.pexels_app.db.dao.PicDao
import ru.myapp.pexels_app.model.CuratedPicsResponse
import ru.myapp.pexels_app.model.DetailPicResponse
import ru.myapp.pexels_app.model.converter.Converter

@Database(
    entities = [
        DetailPicResponse::class,
        CuratedPicsResponse.Photo::class
    ], version = 1
)
@TypeConverters(Converter::class)
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
                )
//                    .addMigrations(MIGRATION_1_2)
                    .fallbackToDestructiveMigration()
                    .build()
                database = instance
                instance
            }
        }
    }
}