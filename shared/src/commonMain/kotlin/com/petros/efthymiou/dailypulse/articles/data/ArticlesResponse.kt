package com.petros.efthymiou.dailypulse.articles.data

import com.petros.efthymiou.dailypulse.articles.data.ArticleRaw
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class ArticlesResponse(
    @SerialName("status") val status: String,
    @SerialName("totalResults") val totalResults: Int,
    @SerialName("articles") val articles: List<ArticleRaw>
)