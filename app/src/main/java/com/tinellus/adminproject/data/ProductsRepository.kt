package com.tinellus.adminproject.data

import com.tinellus.adminproject.data.model.Product
import com.tinellus.adminproject.data.model.Products
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {
    suspend fun getProductsList(): Flow<Result<List<Product>>>
}