package com.example.route

import com.example.datasource.FoodDataSource
import com.example.datasource.YandexDataSource
import com.example.model.YandexTranslateModel
import io.ktor.client.call.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.searchRouting() {
    route("/search") {
        get {
            val word = call.request.queryParameters["word"]
            val top = call.request.queryParameters["top"]?.toInt() ?: 10
            if (!word.isNullOrBlank()) {
                val regEx = "[а-я]+".toRegex(setOf(RegexOption.IGNORE_CASE))
                val isRuLang = word.matches(regEx)
                val targetWord = if (isRuLang) {
                    val translateResponse: YandexTranslateModel = YandexDataSource.translate(word, "en").body()
                    if(translateResponse.translations.isNotEmpty()) {
                        translateResponse.translations.first().text
                    } else {
                        word
                    }
                } else {
                    word
                }

                val responseFood = FoodDataSource.getProductByName(targetWord)
                val result = responseFood.take(top).map {
                    val name = it.name
                    val responseTranslate = YandexDataSource.translate(name, "ru")
                    val translateResponse: YandexTranslateModel = responseTranslate.body()
                    it.name = translateResponse.translations.firstOrNull()?.text ?: name
                    it
                }
                call.respond(result)
            } else {
                call.respondText("Word is Empty", status = HttpStatusCode.BadRequest)
            }
        }
    }
}