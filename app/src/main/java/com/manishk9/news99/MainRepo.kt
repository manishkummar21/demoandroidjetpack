package com.manishk9.news99

import com.manishk9.news99.entities.HeadLine
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MainRepo @Inject constructor(private val apiHelper: ApiHelper, private val db: AppDatabase) {

    fun getTopHeadLines() = flow {
        emit(apiHelper.getTopHeadlines())
    }

    fun insertHeadLines(data: List<HeadLine>) = flow {
        emit( db.headlineDao().deleteAndInsert(headLine = data))
    }

    fun getTopHeadlinesFromDB() = flow{
        emit(db.headlineDao().getallTopHeadling())
    }

    fun getHeadLineDetailsByID(id:Int) = flow {
        emit(db.headlineDao().getHeadlineByID(id))
    }
}