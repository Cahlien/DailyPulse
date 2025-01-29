package com.petros.efthymiou.dailypulse.articles

import com.petros.efthymiou.dailypulse.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ArticlesViewModel: BaseViewModel() {
    private val _articlesState: MutableStateFlow<ArticleState> = MutableStateFlow(ArticleState(loading = true))
    val articlesState: StateFlow<ArticleState> = _articlesState

    init {
        getArticles()
    }

    private fun getArticles() {
        scope.launch {
            val fetchedArticles = fetchArticles()
            delay(500)
            _articlesState.emit(ArticleState(articles = fetchedArticles))
        }
    }

    suspend fun fetchArticles(): List<Article> = mockArticles

    private val mockArticles = listOf(
        Article(
            "Article 1",
            "Description 1",
            "https://images.unsplash.com/photo-1534318400171-5d69d3e4c394?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1770&q=80",
            "2023-02-01T00:00:00Z"
        ),
        Article(
            "Article 2",
            "Description 2",
            "https://images.unsplash.com/photo-1517849845537-4d257902454a?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1770&q=80",
            "2023-02-02T00:00:00Z"
            ),
        Article(
            "Article 3",
            "Description 3",
            "https://images.unsplash.com/photo-1581357825340-32259110788a?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1770&q=80",
            "2023-02-03T00:00:00Z"
            )
    )
}