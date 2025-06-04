package com.taoc.verenime.ui.detail

import com.taoc.verenime.api.responses.AnimeDetailData
import com.taoc.verenime.api.service.ApiService

class DetailAnimeRepository{
    private val api = ApiService.create()

    suspend fun getAnimeDetail(animeId: String): AnimeDetailData? {
        return api.getAnimeDetail(animeId).data
    }
}