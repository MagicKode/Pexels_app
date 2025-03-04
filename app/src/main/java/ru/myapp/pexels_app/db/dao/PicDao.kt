package ru.myapp.pexels_app.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.myapp.pexels_app.model.DetailPicResponse

@Dao
interface PicDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(pic: DetailPicResponse)

    @Query("SELECT * FROM images WHERE id = :id")
    suspend fun getPicById(id: Long)
}