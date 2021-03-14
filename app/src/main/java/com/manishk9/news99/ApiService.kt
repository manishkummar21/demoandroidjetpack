package com.manishk9.news99

import com.manishk9.news99.model.HeadLineResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("top-headlines")
    suspend fun getTopHeadlines(@Query("country") country: String):Response<HeadLineResponse>

}