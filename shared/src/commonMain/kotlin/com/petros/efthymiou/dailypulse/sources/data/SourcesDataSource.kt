package com.petros.efthymiou.dailypulse.sources.data

import petros.efthymiou.dailypulse.db.DailyPulseDatabase

class SourcesDataSource(private val db: DailyPulseDatabase) {
    fun getAllSources(): List<SourceRaw> = db.dailyPulseDatabaseQueries.selectAllSources(::mapSource).executeAsList()

    fun clearSources() = db.dailyPulseDatabaseQueries.removeAllSources()

    private fun mapSource(
        id: String,
        name: String,
        desc: String,
        language: String,
        country: String
    ): SourceRaw = SourceRaw(id, name, desc, language, country)

    internal fun createSources(sources: List<SourceRaw>) {
        db.dailyPulseDatabaseQueries.transaction {
            sources.forEach {
                db.dailyPulseDatabaseQueries.insertSource(it.id, it.name, it.desc, it.language, it.country)
            }
        }
    }
}