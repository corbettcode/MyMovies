package com.corbettcode.mymovies

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.window.ComposeUIViewController
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class, ExperimentalFoundationApi::class)
fun MainViewController() = ComposeUIViewController { App() }