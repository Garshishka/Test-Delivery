package ru.garshishka.testdelivery.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.garshishka.testdelivery.dto.Food
import ru.garshishka.testdelivery.repository.FoodRepository
import ru.garshishka.testdelivery.webapi.DataFeedState
import javax.inject.Inject

@HiltViewModel
class FoodViewModel @Inject constructor(
    private val foodRepository: FoodRepository,
) : ViewModel() {
    val foodData: LiveData<List<Food>>
        get() = foodRepository.foodData

    private val _dataState = MutableLiveData<DataFeedState>(DataFeedState.Idle)
    val dataState: LiveData<DataFeedState>
        get() = _dataState


    init {
        load()
    }

    fun load() = viewModelScope.launch {
        _dataState.value = DataFeedState.Loading

        try{
            foodRepository.getAll()
            _dataState.value = DataFeedState.Idle
        } catch (e:Exception){
            _dataState.value = DataFeedState.Error
        }
    }
}