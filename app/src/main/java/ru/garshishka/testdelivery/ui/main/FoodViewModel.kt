package ru.garshishka.testdelivery.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.garshishka.testdelivery.dto.Food
import ru.garshishka.testdelivery.webapi.DataFeedState
import ru.garshishka.testdelivery.webapi.getApiService

class FoodViewModel : ViewModel() {
    private val _foodData = MutableLiveData<List<Food>>()
    val foodData: LiveData<List<Food>>
        get() = _foodData

    private val _dataState = MutableLiveData<DataFeedState>(DataFeedState.Idle)
    val dataState: LiveData<DataFeedState>
        get() = _dataState


    init {
        load()
    }

    fun load() = viewModelScope.launch {
        _dataState.value = DataFeedState.Loading

        val apiService = getApiService()

        val response = apiService.getProducts()
        if (!response.isSuccessful) {
            _dataState.value = DataFeedState.Error
            throw RuntimeException(response.message().toString())
        }
        val foodList = response.body() ?: throw RuntimeException("body is null")
        _foodData.value = foodList
        foodList.forEach {
            println(it)
        }
        _dataState.value = DataFeedState.Idle
    }
}