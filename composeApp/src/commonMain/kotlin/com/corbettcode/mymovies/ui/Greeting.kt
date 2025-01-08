package com.jackz.kmmovies

import com.corbettcode.mymovies.getPlatform

class Greeting {
    private val platform = getPlatform()

    fun greet(): String {
        return "Hello, ${platform.name}!"
    }
//    fun greeting(): String {
//        return "Movies, ${Platform().platform}!"
//    }
}