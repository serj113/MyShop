package com.serj113.domain.repository

import com.serj113.model.CartProduct
import com.serj113.model.Product

interface StoreRepository {
    suspend fun fetchProductList(): List<Product>

    suspend fun fetchCategoryList(): List<String>

    suspend fun addProductToCart(productId: Int, quantity: Int): Boolean

    suspend fun removeProductFromCart(productId: Int): Boolean

    suspend fun fetchCart(): List<CartProduct>

    suspend fun checkoutCart(): Boolean
}