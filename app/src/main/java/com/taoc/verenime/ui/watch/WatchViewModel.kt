package com.taoc.verenime.ui.watch

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taoc.verenime.api.responses.Data
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WatchViewModel(private val repository: WatchRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<WatchUiState>(WatchUiState.Loading)
    val uiState: StateFlow<WatchUiState> = _uiState

    var currentStreamingUrl by mutableStateOf<String?>(null)
        private set

    fun loadWatchData(episodeId: String, serverId: String? = null) {
        viewModelScope.launch {
            try {
                _uiState.value = WatchUiState.Loading
                val response = repository.getWatchData(episodeId, serverId)
                val data = response.data
                currentStreamingUrl = data?.defaultStreamingUrl
                _uiState.value = WatchUiState.Success(data)
            } catch (e: Exception) {
                _uiState.value = WatchUiState.Error(e.message ?: "Unknown Error")
            }
        }
    }

    fun switchServer(serverId: String, episodeId: String) {
        loadWatchData(episodeId, serverId)
    }
}

sealed class WatchUiState {
    object Loading : WatchUiState()
    data class Success(val data: Data?) : WatchUiState()
    data class Error(val message: String) : WatchUiState()
}