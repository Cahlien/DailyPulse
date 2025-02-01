package com.petros.efthymiou.dailypulse.articles.data

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable class ArticleRaw(
    @SerialName("title") val title: String,
    @SerialName("description") val description: String?,
    @SerialName("publishedAt") val date: String,
    @SerialName("urlToImage") val imageUrl: String?
)