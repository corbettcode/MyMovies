package com.corbettcode.mymovies.di

import com.corbettcode.mymovies.data.remote.TmdbRepositoryActual
import com.corbettcode.mymovies.ui.ApplicationViewModel
import pe.fernan.kmp.tmdb.data.remote.TmdbClientApi

object DataModule {
    val api = TmdbClientApi
    val tmdbRepository = TmdbRepositoryActual(api)
}

object ApplicationModule {
    val applicationViewModel = ApplicationViewModel(DataModule.tmdbRepository)
}
