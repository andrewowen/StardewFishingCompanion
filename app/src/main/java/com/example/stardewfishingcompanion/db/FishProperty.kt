package com.example.stardewfishingcompanion.db

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FishData(
    val id: Int,
    val name: String,
    val description: String,
    val price: Price,
    val location: List<String>,
    val times: List<String>,
    val season: List<String>,
    val weather: List<String>,
    val sizeRange: String,
    val difficulty: Int,
    val behavior: String,
    val bundle: String?,
    val recipes: List<String>?,
    val lovedBy: String?,
    val imgSrc: String

)


@JsonClass(generateAdapter = true)
data class Price(
    val default: Int,
    val silver: Int,
    val gold: Int,
    val iridium: Int
)