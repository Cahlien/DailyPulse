package com.petros.efthymiou.dailypulse.sources.application

import com.petros.efthymiou.dailypulse.sources.data.SourcesRepository
import com.petros.efthymiou.dailypulse.sources.data.SourceRaw

class SourcesUseCase(private val repo: SourcesRepository) {
    suspend fun getSources(): List<Source> {
        val sourcesRaw = repo.getAllSources()
        return mapSources(sourcesRaw)
    }

    private fun mapSources(sourcesRaw: List<SourceRaw>): List<Source> = sourcesRaw.map {
        Source(
            id = it.id,
            name = it.name,
            desc = it.desc,
            origin = mapOrigin(it)
        )
    }

    private fun mapOrigin(raw: SourceRaw) = "${raw.country} - ${raw.language}"
}