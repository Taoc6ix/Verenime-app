package com.taoc.verenime.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taoc.verenime.api.responses.AnimeDetailData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: DetailAnimeRepository = DetailAnimeRepository()
) : ViewModel() {
    private val _animeDetail = MutableStateFlow<AnimeDetailData?>(null)
    val animeDetail: StateFlow<AnimeDetailData?> = _animeDetail

    fun getAnimeDetail(animeId: String) {
        viewModelScope.launch {
            _animeDetail.value = repository.getAnimeDetail(animeId)
        }
    }
}