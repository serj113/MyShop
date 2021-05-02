package com.serj113.presentation.cart

import com.serj113.model.CartProduct

sealed class CartViewState {
    data class Load(val cartProductList: List<CartProduct>): CartViewState()
    object Loading: CartViewState()
}