package ru.myapp.pexels_app.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.myapp.pexels_app.model.CuratedPicsResponse
import ru.myapp.pexels_app.model.SearchPicsResponse

@Dao
interface PicDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPic(pic: CuratedPicsResponse.Photo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSearchPic(pic: SearchPicsResponse.Photo)

    @Query("SELECT * FROM curated_photo ORDER BY id ASC")
    suspend fun getAllPics() : List<CuratedPicsResponse.Photo>


}