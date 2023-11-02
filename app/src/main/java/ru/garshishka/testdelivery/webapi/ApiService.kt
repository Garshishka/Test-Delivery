package ru.garshishka.testdelivery.webapi

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Headers
import ru.garshishka.testdelivery.BuildConfig
import ru.garshishka.testdelivery.dto.Food


interface ApiService {
    @Headers(
        "accept: application/json, text/plain, /",
        "content-type: application/json"
    )
    @GET("product")
    suspend fun getProducts(): Response<List<Food>>
}

fun getApiService(): ApiService {
    val logging = HttpLoggingInterceptor().apply {
        if (BuildConfig.DEBUG) {
            level = HttpLoggingInterceptor.Level.HEADERS
        }
    }

    val okClient = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .client(okClient)
        .baseUrl("https://6543f4f201b5e279de21322b.mockapi.io/food/")
        .build()

    return retrofit.create<ApiService>()
}