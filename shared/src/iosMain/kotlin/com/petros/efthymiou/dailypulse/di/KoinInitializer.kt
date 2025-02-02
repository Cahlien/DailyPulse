package com.petros.efthymiou.dailypulse.di

import com.petros.efthymiou.dailypulse.articles.presentation.ArticlesViewModel
import com.petros.efthymiou.dailypulse.sources.presentation.SourcesViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin

import org.koin.core.component.inject

fun initKoin() {
    val modules = sharedModules + databaseModule

    startKoin{
        modules(modules)
    }
}

class SourcesInjector : KoinComponent {
    val sourcesViewModel: SourcesViewModel by inject()
}

class ArticlesInjector : KoinComponent {
    val articlesViewModel: ArticlesViewModel by inject()
}