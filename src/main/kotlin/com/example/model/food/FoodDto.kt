package com.example.model.food

@kotlinx.serialization.Serializable
data class FoodDto(
    val image: String,
    var name: String,
    val kiloCalories: Int,
    val protein: Int,
    val fat: Int,
    val carbohydrate: Int,
    var count: Int = 0,
    var docId: String? = null
) {
    fun incCount(): FoodDto {
        ++count
        return this
    }

    fun decCount(): FoodDto {
        --count
        return this
    }
}