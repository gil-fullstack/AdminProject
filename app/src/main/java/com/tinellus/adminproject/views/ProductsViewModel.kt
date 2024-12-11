package com.tinellus.adminproject.views

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tinellus.adminproject.data.ProductsRepository
import com.tinellus.adminproject.data.model.Product
import com.tinellus.adminproject.data.Result
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductsViewModel(
    private val productsRepository: ProductsRepository
): ViewModel() {
    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products = _products.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    private val _showErrorTostChannel = Channel<Boolean>()
    val showErrorToastChannel = _showErrorTostChannel.receiveAsFlow()

    init {
        viewModelScope.launch {
            productsRepository.getProductsList().collectLatest { result ->
                when (result) {
                    is Result.Error -> {
                        _showErrorTostChannel.send(true)
                    }
                    is Result.Loading -> {
                        _isLoading.value = true
                    }
                    is Result.Success -> {
                        _isLoading.value = false
                        result.data?.let { products ->
                            _products.update  { products }
                        }
                    }
                }
            }
        }
    }
}