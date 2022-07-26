package com.example.datasource

import com.example.model.YandexIom
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

object YandexUtilsDataSource {
    private val scopeCo by lazy {
        CoroutineScope(Dispatchers.IO)
    }
    private const val REPEAT_COUNT = 1000
    private const val DELAY = 3600000L
    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
        defaultRequest {
            contentType(ContentType.Application.Json)
            url {
                protocol = URLProtocol.HTTPS
                host = BASE_URL_YANDEX_UTILS
                path(END_POINT_YANDEX_UTILS)
            }
        }
    }

    fun refreshToken() {
        scopeCo.launch {
            repeat(REPEAT_COUNT) {
                val response = client.post {
                    setBody(YandexIom(REMOTE_TOKEN))
                }
                println(response.status)
                println(response.request.url)
                println(response.request.content)
                val token = response.body<YandexIom>()
                TOKEN = "Bearer ${token.iamToken}"
                println(TOKEN)
                delay(DELAY)
            }
        }.start()
    }
}