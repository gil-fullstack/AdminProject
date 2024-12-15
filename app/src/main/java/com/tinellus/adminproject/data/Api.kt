package com.tinellus.adminproject.data

import androidx.compose.ui.graphics.Path
import com.tinellus.adminproject.data.model.Product
import com.tinellus.adminproject.data.model.Products
import com.tinellus.adminproject.views.productsRequest

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST

interface Api {

    @GET("products")
    suspend fun getProductsList(): Products

    @POST("products")
    suspend fun createProduct(@Body productsRequest: productsRequest): Product

    @DELETE("products/{id}")
    suspend fun deleteProduct(@retrofit2.http.Path("id") id: Int): Response<Void>

    companion object {
//        const val BASE_URL = "https://api.ciafonline.com.br/"
        const val BASE_URL = "http://10.0.2.2:8080/"

//        private val loggingInterceptor = HttpLoggingInterceptor()
//            .apply { setLevel(HttpLoggingInterceptor.Level.BODY) }
//
//        private val okHttpClient = OkHttpClient.Builder()
//            .addInterceptor(loggingInterceptor).build()
//
//        val api = Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .client(okHttpClient)
//            .build()

    }

    fun createRetrofitInstance(): Retrofit {

        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()


        val retrofit = Retrofit.Builder()
            .baseUrl(Api.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) // Or your preferred converter
            .client(client)
            .build()
        return retrofit
    }
}