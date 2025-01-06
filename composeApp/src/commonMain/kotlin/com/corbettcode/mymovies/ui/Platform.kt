package com.corbettcode.mymovies.ui

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform