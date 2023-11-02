package ru.garshishka.testdelivery.dto

import com.google.gson.annotations.SerializedName

data class Food(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("price")
    val price: Double,
    @SerializedName("image")
    val imageUrl: String,
)
