package com.manishk9.news99

import androidx.room.Database
import androidx.room.RoomDatabase
import com.manishk9.news99.dao.HeadLineDao
import com.manishk9.news99.entities.HeadLine


@Database(entities = arrayOf(HeadLine::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun headlineDao(): HeadLineDao
}