package com.taoc.verenime.ui.streaming

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taoc.verenime.api.responses.DetailAnimeResponse
import com.taoc.verenime.api.responses.ServerData
import com.taoc.verenime.api.responses.StreamingResponse
import com.taoc.verenime.api.service.ApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class StreamingViewModel : ViewModel() {
    private val api = ApiService.create()

    private val _episodeData = MutableStateFlow<StreamingResponse?>(null)
    val episodeData: StateFlow<StreamingResponse?> = _episodeData

    private val _animeDetail = MutableStateFlow<DetailAnimeResponse?>(null)
    val animeDetail: StateFlow<DetailAnimeResponse?> = _animeDetail

    private val _serverData = MutableStateFlow<ServerData?>(null)
    val serverData: StateFlow<ServerData?> = _serverData

    fun fetchEpisode(episodeId: String) {
        viewModelScope.launch {
            try {
                _episodeData.value = api.getStreamingData(episodeId)
            } catch (e: Exception) {
                Log.e("StreamingViewModel", "Error fetching episode", e)
            }
        }
    }

    fun fetchServer(serverId: String) {
        viewModelScope.launch {
            try {
                _episodeData.value = api.getServerData(serverId)
            } catch (e: Exception) {
                Log.e("StreamingViewModel", "Error fetching server data", e)
            }
        }
    }

    fun fetchAnimeDetail(animeId: String) {
        viewModelScope.launch {
            try {
                _animeDetail.value = api.getAnimeDetail(animeId)
            } catch (e: Exception) {
                Log.e("StreamingViewModel", "Error fetching anime detail", e)
            }
        }
    }
}
