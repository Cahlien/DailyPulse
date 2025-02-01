package com.petros.efthymiou.dailypulse.articles.data

import com.petros.efthymiou.dailypulse.articles.data.ArticleRaw
import petros.efthymiou.dailypulse.db.DailyPulseDatabase

class ArticlesDataSource(private val database: DailyPulseDatabase) {
    fun getAllArticules(): List<ArticleRaw> = database.dailyPulseDatabaseQueries.selectAllArticles(::mapToArticleRaw).executeAsList()

    fun insertArticles(articles: List<ArticleRaw>) {
        database.dailyPulseDatabaseQueries.transaction {
            articles.forEach {
                articleRaw -> insertArticle(articleRaw)
            }
        }
    }

    fun clearArticles() = database.dailyPulseDatabaseQueries.removeAllArticles()

    private fun insertArticle(articleRaw: ArticleRaw) {
        database.dailyPulseDatabaseQueries.insertArticle(
            title = articleRaw.title,
            desc = articleRaw.description,
            date = articleRaw.date,
            imageUrl = articleRaw.imageUrl
        )
    }

    private fun mapToArticleRaw(
        title: String,
        description: String?,
        date: String,
        imageUrl: String?
    ) = ArticleRaw(title, description, date, imageUrl)
}