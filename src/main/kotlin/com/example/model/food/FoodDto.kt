package com.example.model.food

@kotlinx.serialization.Serializable
data class FoodDto(
    val image: String,
    var name: String,
    val kiloCalories: Double,
    val protein: Double,
    val fat: Double,
    val carbohydrate: Double,
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