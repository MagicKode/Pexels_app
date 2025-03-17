package ru.myapp.pexels_app.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.myapp.pexels_app.model.CuratedPicsResponse

@Dao
interface PicDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPic(pic: CuratedPicsResponse.Photo)

    @Query("SELECT * FROM curated_photo ORDER BY id ASC")
    suspend fun getAllPics() : List<CuratedPicsResponse.Photo>

    @Query("SELECT * FROM curated_photo WHERE id = :id")
    suspend fun getPicById(id: Int): CuratedPicsResponse.Photo
}