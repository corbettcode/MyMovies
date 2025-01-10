package com.corbettcode.mymovies.ui

import androidx.lifecycle.ViewModel
import com.corbettcode.mymovies.data.remote.TmdbRepositoryActual
import com.corbettcode.mymovies.domain.repository.TmdbRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ApplicationViewModel(private val repository: TmdbRepository) : ViewModel() {
    private val loading = MutableStateFlow(false)
    val isLoading get() = loading.asStateFlow()

}