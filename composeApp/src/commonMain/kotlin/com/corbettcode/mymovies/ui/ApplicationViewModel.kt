package com.corbettcode.mymovies.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.corbettcode.mymovies.domain.repository.TmdbRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch

class ApplicationViewModel(private val repository: TmdbRepository) : ViewModel() {
    private val loading = MutableStateFlow(false)
    val isLoading get() = loading.asStateFlow()

    @FlowPreview
    fun movieSearch(searchKey: String) {
        viewModelScope.launch {
            flowOf(searchKey).debounce(300)
                .filter {
                    it.trim().isNotEmpty()
                }
                .distinctUntilChanged()
                .flatMapLatest {
                    repository.getSearchList(it)
                }
                .collect {
                    when (it) {
                        is UiState.Loading -> {
                            loading.value = true
                        }

                        is UiState.Success -> {
                            _movieSearchDate.value = it.data.results
                            loading.value = false
                        }

                        is UiState.Error -> {
                            loading.value = false
                        }
                    }
                }
        }
    }
}