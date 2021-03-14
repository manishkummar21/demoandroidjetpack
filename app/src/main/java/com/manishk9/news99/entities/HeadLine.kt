package com.manishk9.news99.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class HeadLine(
    @PrimaryKey(autoGenerate = true)
    val headlineID: Int,
    @Embedded
    val source: HeadLineSource,
    @ColumnInfo(name = "author")
    val author: String?,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "description")
    val description: String?,
    @ColumnInfo(name = "url")
    val url: String?,
    @ColumnInfo(name = "urlToImage")
    val urlToImage: String?,
    @ColumnInfo(name = "publishedAt")
    val publishedAt: String,
    @ColumnInfo(name = "content")
    val content: String?
)

data class HeadLineSource(
    @ColumnInfo(name = "id")
    val id: String?,
    @ColumnInfo(name = "name")
    val name: String
)