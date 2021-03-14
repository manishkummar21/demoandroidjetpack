package com.manishk9.news99

import com.manishk9.news99.model.HeadLineResponse
import retrofit2.Response

interface ApiHelper {
  suspend fun getTopHeadlines():Response<HeadLineResponse>
}