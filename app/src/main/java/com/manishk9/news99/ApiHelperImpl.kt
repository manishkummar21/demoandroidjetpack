package com.manishk9.news99

import com.manishk9.news99.model.HeadLineResponse
import retrofit2.Response
import java.util.*
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper
{
    override suspend fun getTopHeadlines(): Response<HeadLineResponse> {
       return apiService.getTopHeadlines(Locale.getDefault().country)
    }
}