package com.corbettcode.mymovies.ui.details.artist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.corbettcode.mymovies.domain.repository.TmdbRepository
import com.corbettcode.mymovies.navigation.MovieSection
import com.corbettcode.mymovies.utility.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class ArtistDetailViewModel : ViewModel() {
    private val repo = TmdbRepository()
    private val _artistDetailResponse =
        MutableStateFlow<MovieSection.ArtistDetail?>(null)
    val artistDetailResponse get() = _artistDetailResponse.asStateFlow()

    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading get() = _isLoading.asStateFlow()

    fun artistDetail(personId: Int) {
        viewModelScope.launch {
            viewModelScope.launch {
                repo.artistDetail(personId).onEach {
                    when (it) {
                        is UiState.Loading -> {
                            _isLoading.value = true
                        }

                        is UiState.Success -> {
                            _artistDetailResponse.value = it.data
                            _isLoading.value = false
                        }

                        is UiState.Error -> {
                            _isLoading.value = false
                        }
                    }
                }.launchIn(viewModelScope)
            }
        }
    }
}