package com.corbettcode.mymovies.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.corbettcode.mymovies.data.model.tv.TvSeriesDetail
import com.corbettcode.mymovies.domain.model.TmdbResult
import com.corbettcode.mymovies.utility.UiState
import data.repository.Repository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch

class ApplicationViewModel(private val repository: Repository) : ViewModel() {
    private val loading = MutableStateFlow(false)
    val isLoading get() = loading.asStateFlow()
    private val _movieSearchData = MutableStateFlow<List<TmdbResult>>(arrayListOf())
    val movieSearchData get() = _movieSearchData.asStateFlow()
    private val _tvSeriesSearchData = MutableStateFlow<List<TvSeriesDetail>>(arrayListOf())
    val tvSeriesSearchData get() = _tvSeriesSearchData.asStateFlow()

    @FlowPreview
    @OptIn(ExperimentalCoroutinesApi::class)
    fun movieSearch(searchKey: String) {
        viewModelScope.launch {
            flowOf(searchKey)
                .debounce(300)
                .filter {
                    it.trim().isNotEmpty()
                }
                .distinctUntilChanged()
                .flatMapLatest {
                    repository.searchMovie(it)
                }
                .collect {
                    when (it) {
                        is UiState.Loading -> {
                            loading.value = true
                        }

                        is UiState.Success -> {
                            _movieSearchData.value = it.data.results
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