package com.example.route

import com.example.datasource.YandexDataSource
import com.example.model.YandexTranslateModel
import io.ktor.client.call.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.searchRouting() {
    route("/search") {
        get {
            val response = YandexDataSource.translate("hello", "ru")
            val translateResponse: YandexTranslateModel = response.body()
            call.respond(translateResponse)
        }
    }
}