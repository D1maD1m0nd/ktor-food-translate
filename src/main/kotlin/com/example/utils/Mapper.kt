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
        this.nutrients?.ENERC_KCAL?.toInt() ?: 0,
        this.nutrients?.PROCNT?.toInt() ?: 0,
        this.nutrients?.FAT?.toInt() ?: 0,
        this.nutrients?.CHOCDF?.toInt() ?: 0
    )
}
