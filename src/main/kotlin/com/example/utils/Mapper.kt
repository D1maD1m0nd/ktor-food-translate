package com.example.utils

import com.example.model.food.Food
import com.example.model.food.FoodDto

fun Food.toFoodListDto(): List<FoodDto> {
    val foods = this.hints.map {
        it.food.toFoodDto()
    }

    return foods
}

fun Food.toFoodDto(): FoodDto {
    return FoodDto(
        this.image ?: "",
        this.label ?: "",
        this.nutrients?.ENERC_KCAL ?: 0.0,
        this.nutrients?.PROCNT ?: 0.0,
        this.nutrients?.FAT ?: 0.0,
        this.nutrients?.CHOCDF ?: 0.0
    )
}
