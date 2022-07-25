package com.example.datasource

import com.example.model.YandexTranslateItemRequest
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json


object YandexDataSource {
    private const val BASE_URL = "translate.api.cloud.yandex.net"
    private const val BASE_END_POINT = "translate/v2/translate"
    private const val TOKEN =
        "Bearer t1.9euelZqNyY2Jm4yLmZyanZHNlZ6bk-3rnpWay8yLjsnGio-Ty8rGi8_GlYnl9PdEdwhp-e8GX0PD3fT3BCYGafnvBl9Dww.Lifc3I9e1OrX1oaEtAeOxXePI51IU91ckr9V-66iNQRp0q9RDy-mpZvLNKVDVGDJp9DixJXw6yT_6cAZSGhqCw"
    private const val FOLDER_ID = "b1gra96in6jjljivo0bl"
    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
            })
        }
        defaultRequest {
            contentType(ContentType.Application.Json)
            url {
                protocol = URLProtocol.HTTPS
                host = BASE_URL
            }
            header("Authorization", TOKEN)
        }
    }

    fun translate(word: String, lang: String): HttpResponse {
        return runBlocking {
            client.post {
                setBody(YandexTranslateItemRequest(word, FOLDER_ID, lang))
                url {
                    path(BASE_END_POINT)
                }
            }
        }
    }
}