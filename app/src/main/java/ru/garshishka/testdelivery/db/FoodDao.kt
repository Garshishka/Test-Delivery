package ru.garshishka.testdelivery.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface FoodDao {

    @Query("SELECT * FROM food_table")
    fun getAll(): LiveData<List<FoodEntity>>

    @Query("DELETE FROM food_table")
    suspend fun clearTable()

    @Upsert
    suspend fun saveAll(list: List<FoodEntity>)
}
