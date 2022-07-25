package com.example.model.food

@kotlinx.serialization.Serializable
data class Food(
    val label: String? = null,
    val hints: List<ParsedItem> = ArrayList(),
    val image: String? = null,
    val nutrients: Nutrients? = null
)

