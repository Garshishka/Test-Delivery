package ru.garshishka.testdelivery.repository

import androidx.lifecycle.LiveData
import ru.garshishka.testdelivery.dto.Food

interface FoodRepository {
    val foodData: LiveData<List<Food>>
    suspend fun getAll()
}