package com.petros.efthymiou.dailypulse.articles.presentation

import com.petros.efthymiou.dailypulse.BaseViewModel
import com.petros.efthymiou.dailypulse.articles.data.ArticlesService
import com.petros.efthymiou.dailypulse.articles.presentation.ArticlesState
import com.petros.efthymiou.dailypulse.articles.application.ArticlesUseCase
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

class ArticlesViewModel(private val useCase: ArticlesUseCase): BaseViewModel() {
    private val _articlesState: MutableStateFlow<ArticlesState> = MutableStateFlow(ArticlesState(loading = true))
    val articlesState: StateFlow<ArticlesState> = _articlesState

    init {
        val client = HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
        }

        val service = ArticlesService(client)

        getArticles()
    }

    fun getArticles(forceFetch: Boolean = false) {
        scope.launch {
            scope.launch {
                val fetchedArticles = useCase.getArticles(forceFetch)

                _articlesState.emit(ArticlesState(articles = fetchedArticles))
            }
        }
    }
}