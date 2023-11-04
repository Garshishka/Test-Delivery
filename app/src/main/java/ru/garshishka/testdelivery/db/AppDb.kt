package ru.garshishka.testdelivery.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.garshishka.testdelivery.db.FoodEntity

@Database(entities = [FoodEntity::class], version = 1, exportSchema = false)
abstract class AppDb : RoomDatabase() {
    abstract fun foodDao(): FoodDao
}