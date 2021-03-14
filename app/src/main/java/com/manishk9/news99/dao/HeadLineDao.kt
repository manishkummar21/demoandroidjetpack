package com.manishk9.news99.dao

import androidx.room.*
import com.manishk9.news99.entities.HeadLine

@Dao
interface HeadLineDao {

    @Insert
    suspend fun insertAll(headLine: List<HeadLine>)

    @Query("DELETE FROM HeadLine")
    suspend fun deleteAll()

    @Transaction
    suspend fun deleteAndInsert( headLine: List<HeadLine>) : List<HeadLine>{
        deleteAll()
        insertAll(headLine)
        return getallTopHeadling()
    }

    @Query("SELECT * FROM HeadLine")
    suspend fun getallTopHeadling():List<HeadLine>

    @Query("SELECT * FROM HeadLine WHERE headlineID = :id")
    suspend fun getHeadlineByID(id:Int):HeadLine

}