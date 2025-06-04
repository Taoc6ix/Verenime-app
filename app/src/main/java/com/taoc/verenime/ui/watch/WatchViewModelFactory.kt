package com.taoc.verenime.ui.watch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class WatchViewModelFactory(private val repository: WatchRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WatchViewModel::class.java)) {
            return WatchViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
