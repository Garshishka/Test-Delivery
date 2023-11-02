package ru.garshishka.testdelivery.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.garshishka.testdelivery.webapi.getApiService

class FoodViewModel : ViewModel() {


    init {
        load()
    }

    private fun load() = viewModelScope.launch {
        //_dataState.value = DataFeedState.Loading

        val apiService = getApiService()

        val response = apiService.getProducts()
        if (!response.isSuccessful) {
            //_dataState.value = DataFeedState.Error
            throw RuntimeException(response.message().toString())
        }
        val foodList = response.body() ?: throw RuntimeException("body is null")
        foodList.forEach {
            println(it)
        }
        //_dataState.value = DataFeedState.Idle
    }
}