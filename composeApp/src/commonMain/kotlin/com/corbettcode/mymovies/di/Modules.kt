package com.corbettcode.mymovies.di

import com.corbettcode.mymovies.di.DataModule.tmdbRepository
import com.corbettcode.mymovies.ui.ApplicationViewModel
import data.remote.TmdbApiService
import data.repository.Repository
import pe.fernan.kmp.tmdb.data.remote.TmdbClientApi

object DataModule {
    val api = TmdbClientApi
    val service = TmdbApiService()
    val tmdbRepository = Repository(service)
}

object ApplicationModule {
    val applicationViewModel = ApplicationViewModel(tmdbRepository)
}
