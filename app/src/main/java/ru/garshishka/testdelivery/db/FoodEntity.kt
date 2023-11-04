package ru.garshishka.testdelivery.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.garshishka.testdelivery.dto.Food

@Entity(tableName = "food_table")
data class FoodEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val imageUrl: String,
){
    fun toDto() = Food(id,name,description,price,imageUrl)

    companion object{
        fun fromDto(dto:Food)=
            FoodEntity(dto.id,dto.name,dto.description,dto.price,dto.imageUrl)
    }
}
