package com.app.myinapp.domain.room.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.app.myinapp.domain.model.Photo
import kotlinx.coroutines.flow.Flow

@Dao
interface DaoLiked {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(photo: Photo)

    @Update
    fun update(photo: Photo)

    @Query("DELETE FROM LikedImages WHERE imageId=:id")
    fun delete(id: Int)

    @Query("delete from LikedImages")
    fun deleteAllNotes()

    @Query("SELECT * FROM LikedImages ORDER BY id DESC ")
    fun getAllLiked(): PagingSource<Int, Photo>


    @Query("SELECT * FROM LikedImages")
    fun getAllNotes(): Flow<List<Photo>>

    @Query("SELECT * FROM LikedImages WHERE imageId=:id")
    fun checkLiked(id: Int): Flow<Photo>


    @Query("DELETE FROM LikedImages WHERE imageId=:id")
    fun deleteCor(id: Int): Int
}