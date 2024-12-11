package com.tinellus.adminproject.data

import com.tinellus.adminproject.data.model.Products
import retrofit2.http.GET

interface Api {

    @GET("products")
    suspend fun getProductsList(): Products

    companion object {
//        const val BASE_URL = "https://api.ciafonline.com.br/"
        const val BASE_URL = "http://10.0.2.2:8080/"
    }
}