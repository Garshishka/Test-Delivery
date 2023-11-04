package ru.garshishka.testdelivery.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import ru.garshishka.testdelivery.db.FoodDao
import ru.garshishka.testdelivery.db.FoodEntity
import ru.garshishka.testdelivery.dto.Food
import ru.garshishka.testdelivery.webapi.ApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FoodRepositoryImpl @Inject constructor(
    private val foodDao: FoodDao,
    private val apiService: ApiService,
) : FoodRepository{
    override val foodData: LiveData<List<Food>> = foodDao.getAll().map {
        it.map(FoodEntity::toDto)
    }

    override suspend fun getAll() {
        val response = apiService.getProducts()

        if (!response.isSuccessful) {
            throw RuntimeException(response.code().toString())
        }
        val food = response.body() ?: throw RuntimeException("body is null")

        foodDao.clearTable()
        foodDao.saveAll(food.map(FoodEntity.Companion::fromDto))
    }
}