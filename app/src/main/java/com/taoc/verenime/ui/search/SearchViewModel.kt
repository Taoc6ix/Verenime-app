package com.taoc.verenime.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taoc.verenime.api.responses.AnimeResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SearchViewModel(
    private val repository: SearchRepository = SearchRepository()
) : ViewModel() {
    private val _searchResult = MutableStateFlow<List<AnimeResponse>>(emptyList())
    val searchResult: StateFlow<List<AnimeResponse>> = _searchResult

    fun searchAnime(query: String) {
        viewModelScope.launch {
            _searchResult.value = repository.searchAnime(query)
        }
    }
}