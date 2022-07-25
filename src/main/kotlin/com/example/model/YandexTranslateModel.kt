package com.example.model

import kotlinx.serialization.Serializable

@Serializable
data class YandexTranslateModel(val translations: List<YandexTranslateItem>)

@Serializable
data class YandexTranslateItem(val text: String, val detectedLanguageCode: String)

@Serializable
data class YandexTranslateItemRequest(val texts: String, val folderId: String, val targetLanguageCode: String)