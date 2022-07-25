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