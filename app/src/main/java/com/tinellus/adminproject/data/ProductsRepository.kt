package com.tinellus.adminproject.data

import com.tinellus.adminproject.data.model.Product
import com.tinellus.adminproject.data.model.Products
import com.tinellus.adminproject.views.productsRequest
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {
    suspend fun getProductsList(): Flow<Result<List<Product>>>

    suspend fun createProduct(productsRequest: productsRequest)

}