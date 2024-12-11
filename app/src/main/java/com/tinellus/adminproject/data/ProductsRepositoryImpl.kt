package com.tinellus.adminproject.data

import android.annotation.SuppressLint
import android.net.http.HttpException
import com.tinellus.adminproject.data.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class ProductsRepositoryImpl(
    private val api: Api
) : ProductsRepository  {
    override suspend fun getProductsList(): Flow<Result<List<Product>>>  {
        return flow {
            val productsFromApi = try {
                emit(Result.Loading())
                api.getProductsList()
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Result.Error(e.message + " Error loading products"))
                return@flow
            } catch (@SuppressLint("NewApi") e: HttpException) {
                e.printStackTrace()
                emit(Result.Error(e.message))
                return@flow
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Result.Error(e.message))
                return@flow
            }
            emit(Result.Success(productsFromApi.products))
        }
    }
}