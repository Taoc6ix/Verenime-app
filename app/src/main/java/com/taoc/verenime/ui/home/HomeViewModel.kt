package com.taoc.verenime.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taoc.verenime.api.responses.AnimeResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: HomeRepository = HomeRepository()) : ViewModel() {
    private val _ongoingAnime = MutableStateFlow<List<AnimeResponse>>(emptyList())
    val ongoingAnime: StateFlow<List<AnimeResponse>> = _ongoingAnime

    private val _allAnime = MutableStateFlow<List<AnimeResponse>>(emptyList())
    val allAnime: StateFlow<List<AnimeResponse>> = _allAnime

    private val _completedAnime = MutableStateFlow<List<AnimeResponse>>(emptyList())
    val completedAnime: StateFlow<List<AnimeResponse>> = _completedAnime

    init {
        getOngoingAnime()
        getAllAnime()
        getCompletedAnime()
    }

    fun getOngoingAnime(page: Int = 1) {
        viewModelScope.launch {
            _ongoingAnime.value = repository.getOngoingAnime(page)
        }
    }

    fun getAllAnime(page: Int = 1) {
        viewModelScope.launch {
            _allAnime.value = repository.getAllAnime(page)
        }
    }

    fun getCompletedAnime(page: Int = 1) {
        viewModelScope.launch {
            _completedAnime.value = repository.getCompletedAnime(page)
        }
    }
}