package com.corbettcode.mymovies.data.remote

import com.corbettcode.mymovies.utility.ApplicationConfiguration
import com.corbettcode.mymovies.utility.NetworkConstant
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.takeFrom
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

val client = HttpClient {
    defaultRequest {
        url {
            takeFrom(NetworkConstant.BASE_URL)
            parameters.append("api_key", ApplicationConfiguration.API_KEY)
        }
    }
    expectSuccess = true
    install(HttpTimeout) {
        val timeout = 30000L
        connectTimeoutMillis = timeout
        requestTimeoutMillis = timeout
        socketTimeoutMillis = timeout
    }
    install(Logging) {
        logger = Logger.DEFAULT
        level = LogLevel.HEADERS
        logger = object : Logger {
            override fun log(message: String) {
                println(message)
            }
        }
    }
    install(ContentNegotiation) {
        json(Json {
            ignoreUnknownKeys = true
        })
    }
}