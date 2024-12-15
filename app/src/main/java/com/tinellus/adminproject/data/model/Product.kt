package com.tinellus.adminproject.data.model

import com.google.gson.annotations.Expose

data class Product(
    @Expose val name: String,
    @Expose val stock: Int,
    @Expose val price: Double,
    val id: Int? = null
)