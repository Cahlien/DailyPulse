package com.petros.efthymiou.dailypulse.articles.data

import com.petros.efthymiou.dailypulse.articles.data.ArticleRaw
import com.petros.efthymiou.dailypulse.articles.data.ArticlesDataSource
import com.petros.efthymiou.dailypulse.articles.data.ArticlesService

class ArticlesRepository(private val dataSource: ArticlesDataSource, private val service: ArticlesService) {
    suspend fun getArticles(forceFetch: Boolean): List<ArticleRaw> {
        if(forceFetch) {
            dataSource.clearArticles()
            return fetchArticles()
        }

        val articlesDb = dataSource.getAllArticules()
        println("Go ${articlesDb.size} from the database!")

        if(articlesDb.isEmpty()) {
            return fetchArticles()
        }

        return articlesDb
    }

    private suspend fun fetchArticles(): List<ArticleRaw> {
        val fetchedArticles = service.fetchArticles()
        dataSource.insertArticles(fetchedArticles)
        return fetchedArticles
    }
}