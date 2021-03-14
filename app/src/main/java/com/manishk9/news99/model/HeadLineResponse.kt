package com.manishk9.news99.model

import com.manishk9.news99.entities.HeadLine


data class HeadLineResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<HeadLine>
)