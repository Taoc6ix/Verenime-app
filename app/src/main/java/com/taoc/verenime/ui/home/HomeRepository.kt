package com.taoc.verenime.ui.home

import com.taoc.verenime.api.responses.AnimeResponse
import com.taoc.verenime.api.service.ApiService

class HomeRepository {
    private val api = ApiService.create()

    suspend fun getOngoingAnime(page: Int): List<AnimeResponse> {
        return api.getOngoingAnime(page).data ?.animeList ?: emptyList()
    }

    suspend fun getAllAnime(page: Int): List<AnimeResponse> {
        return api.getAllAnime(page).data ?.animeList ?: emptyList()
    }

    suspend fun getCompletedAnime(page: Int): List<AnimeResponse> {
        return api.getCompletedAnime(page).data ?.animeList ?: emptyList()
    }
}