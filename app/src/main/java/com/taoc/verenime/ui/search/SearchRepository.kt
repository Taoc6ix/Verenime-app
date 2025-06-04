package com.taoc.verenime.ui.search

import com.taoc.verenime.api.responses.AnimeResponse
import com.taoc.verenime.api.service.ApiService

class SearchRepository {
    private val api = ApiService.create()

    suspend fun searchAnime(query: String): List<AnimeResponse> {
        return api.searchAnime(query).data ?.animeList ?: emptyList()
    }
}