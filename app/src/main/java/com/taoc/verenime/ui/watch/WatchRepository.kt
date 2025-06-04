package com.taoc.verenime.ui.watch

import com.taoc.verenime.api.responses.WatchResponse
import com.taoc.verenime.api.service.ApiService

class WatchRepository(private val apiService: ApiService) {
    suspend fun getWatchData(episodeId: String, serverId: String?): WatchResponse {
        return apiService.getStreamingData(episodeId, serverId)
    }
}