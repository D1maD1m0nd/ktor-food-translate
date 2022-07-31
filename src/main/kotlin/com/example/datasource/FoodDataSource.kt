package com.example.datasource

import com.example.model.food.Food
import com.example.model.food.FoodDto
import com.example.utils.toFoodListDto
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
                host = BASE_URL_EDADAM
                parameters.append("app_id", EDADIM_APP_ID)
                parameters.append("app_key", EDADIM_APP_KEY)
                parameters.append("type", "cooking")
            }
        }
    }

    fun getProductByName(
        ingr: String,
    ): List<FoodDto> {
        return runBlocking {
            val response =
                client.get {
                    url {
                        path("/api/food-database/v2/parser")
                        parameters.append("ingr", ingr)
                    }
                }
            response.body<Food>().toFoodListDto()
        }
    }
}