package com.example.model.food

@kotlinx.serialization.Serializable
data class Nutrients(
    val PROCNT: Double,
    val ENERC_KCAL: Double,
    val FAT: Double,
    val CHOCDF: Double,
)