package ru.garshishka.testdelivery.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.garshishka.testdelivery.repository.FoodRepository
import ru.garshishka.testdelivery.repository.FoodRepositoryImpl
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface RepositoryModule {
    @Singleton
    @Binds
    fun bindsFoodRepository(
        foodRepositoryImpl: FoodRepositoryImpl
    ): FoodRepository

}