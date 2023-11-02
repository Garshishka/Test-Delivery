package ru.garshishka.testdelivery.webapi

sealed interface DataFeedState {
    object Idle : DataFeedState
    object Error : DataFeedState
    object Loading : DataFeedState
}