package com.serj113.presentation.productlist

import com.serj113.model.CartProduct

sealed class ProductListViewState {
    data class LoadInitial(val categoryList: List<String>, val cartProductList: List<CartProduct>) : ProductListViewState()
    data class Load(val selectedCategory: String, val cartProductList: List<CartProduct>) : ProductListViewState()
    object Loading : ProductListViewState()
    data class Error(var error: Throwable) : ProductListViewState()
}