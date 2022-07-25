package com.example.model

import kotlinx.serialization.Serializable

@Serializable
data class YandexIom(val yandexPassportOauthToken: String = "", val iamToken: String = "")