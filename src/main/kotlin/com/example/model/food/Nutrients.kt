package com.example.model.food

@kotlinx.serialization.Serializable
data class Nutrients(
    val PROCNT: Double = 0.0,
    val ENERC_KCAL: Double = 0.0,
    val FAT: Double = 0.0,
    val CHOCDF: Double = 0.0,
)