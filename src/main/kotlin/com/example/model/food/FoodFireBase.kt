package com.example.foodnote.data.model.food

data class FoodFireBase(
    val image: String? = null,
    val name: String? = null,
    val kiloCalories: Double? = null,
    val protein: Double? = null,
    val fat: Double? = null,
    val carbohydrate: Double? = null,
    val count: Int? = null,
    val docId: String? = null
)