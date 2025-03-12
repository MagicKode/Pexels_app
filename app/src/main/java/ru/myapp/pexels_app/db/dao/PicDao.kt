package ru.myapp.pexels_app.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.myapp.pexels_app.model.CuratedPicsResponse
import ru.myapp.pexels_app.model.DetailPicResponse

@Dao
interface PicDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPic(pic: CuratedPicsResponse.Photo)

    @Query("SELECT * FROM images WHERE id = :id")
    suspend fun getPicById(id: CuratedPicsResponse.Photo)

    @Query("SELECT * FROM images ORDER BY id ASC")
    fun getAllPics() : LiveData<List<CuratedPicsResponse.Photo>>
}