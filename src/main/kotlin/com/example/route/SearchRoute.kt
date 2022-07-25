package com.example.route

import com.example.datasource.FoodDataSource
import com.example.datasource.YandexDataSource
import com.example.model.YandexTranslateModel
import io.ktor.client.call.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.searchRouting() {
    route("/search") {
        get {
            val responseFood = FoodDataSource.getProductByName("potato")
            val result = responseFood.take(5).map {
                val name = it.name
                val responseTranslate = YandexDataSource.translate(name, "ru")
                val translateResponse: YandexTranslateModel = responseTranslate.body()
                it.name = translateResponse.translations.firstOrNull()?.text ?: name
                it
            }
            call.respond(result)
        }
    }
}