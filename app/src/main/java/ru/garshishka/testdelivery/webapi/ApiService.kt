package ru.garshishka.testdelivery.webapi

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import ru.garshishka.testdelivery.dto.Food


interface ApiService {
    @Headers(
        "accept: application/json, text/plain, /",
        "content-type: application/json"
    )
    @GET("product")
    suspend fun getProducts(): Response<List<Food>>
}