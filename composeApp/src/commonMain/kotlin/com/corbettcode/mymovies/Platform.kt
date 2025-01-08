package com.corbettcode.mymovies

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform