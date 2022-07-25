package com.example.datasource

import com.example.model.food.Food
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json

object FoodDataSource {
    private const val EDADIM_APP_ID = "705a1034"
    private const val EDADIM_APP_KEY = "f42a12def356a6f3b997a9d1e25f63b4"
    private const val BASE_URL = "api.edamam.com/api/"

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
                parameters.append("app_id", EDADIM_APP_ID)
                parameters.append("app_key", EDADIM_APP_KEY)
                parameters.append("type", "cooking")
            }
        }
    }

    fun getProductByName(
        ingr: String,
    ): Food {
        return runBlocking {
            val response = runBlocking {
                client.post {
                    url {
                        parameters.append("ingr", ingr)
                    }
                }
            }
            response.body()
        }
    }
}