package com.corbettcode.mymovies.di

import com.corbettcode.mymovies.data.remote.OLD_TmdbRepositoryActual
import com.corbettcode.mymovies.ui.ApplicationViewModel
import pe.fernan.kmp.tmdb.data.remote.TmdbClientApi

object DataModule {
    val api = TmdbClientApi
    val tmdbRepository = OLD_TmdbRepositoryActual(api)
}

object ApplicationModule {
    val applicationViewModel = ApplicationViewModel(DataModule.tmdbRepository)
}
