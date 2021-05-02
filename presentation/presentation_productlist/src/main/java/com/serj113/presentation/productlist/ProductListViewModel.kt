package com.serj113.presentation.productlist

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import com.serj113.base.presentation.BaseViewModel
import com.serj113.base.presentation.util.Event
import com.serj113.domain.base.Entity
import com.serj113.domain.interactor.AddProductToCartUseCase
import com.serj113.domain.interactor.GetProductByCategoryUseCase
import com.serj113.model.CartProduct
import com.serj113.model.Product
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ProductListViewModel @ViewModelInject constructor(
    private val getProductByCategoryUseCase: GetProductByCategoryUseCase,
    private val addProductToCartUseCase: AddProductToCartUseCase
) : BaseViewModel<ProductListViewState>() {
    private var productListByCategory = hashMapOf<String, List<CartProduct>>()
    private var currentActiveCategory = ""

    fun loadInitialData() = viewModelScope.launch {
        if (productListByCategory.isNotEmpty()) {
            val categoryList = productListByCategory.keys.map { it }
            currentActiveCategory = if (currentActiveCategory.isBlank()) categoryList.first() else currentActiveCategory
            val productList = productListByCategory[currentActiveCategory] ?: listOf()
            _viewState.postValue(Event(ProductListViewState.LoadInitial(categoryList, productList)))
        }
        getProductByCategoryUseCase.invoke().collect { entity ->
            val viewState = when (entity) {
                is Entity.Success -> {
                    productListByCategory = entity.data
                    val categoryList = entity.data.keys.map { it }
                    val activeCategory = if (currentActiveCategory.isBlank()) categoryList.first() else currentActiveCategory
                    val productList = entity.data[activeCategory] ?: listOf()
                    currentActiveCategory = activeCategory
                    ProductListViewState.LoadInitial(categoryList, productList)
                }
                else -> {
                    ProductListViewState.Loading
                }
            }
            _viewState.postValue(Event(viewState))
        }
    }

    fun onClickCategory(category: String) {
        if (currentActiveCategory == category) return
        var cartProductList = listOf<CartProduct>()
        if (productListByCategory.containsKey(category) && !productListByCategory[category].isNullOrEmpty()) {
            productListByCategory[category]?.let {
                currentActiveCategory = category
                cartProductList = it
            }
        }
        _viewState.postValue(
            Event(
                ProductListViewState.Load(
                    currentActiveCategory,
                    cartProductList
                )
            )
        )
    }

    fun onClickProductToCart(cartProduct: CartProduct) = viewModelScope.launch {
        val productCategoryList =
            productListByCategory[currentActiveCategory]?.toMutableList() ?: return@launch
        addProductToCartUseCase.invoke(
            AddProductToCartUseCase.Args(
                cartProduct.productId,
                cartProduct.quantity + 1
            )
        ).collect { entity ->
            if (entity is Entity.Success && entity.data) {
                val cartProductIndex = productCategoryList.indexOf(cartProduct)
                cartProduct.quantity += 1
                productCategoryList[cartProductIndex] = cartProduct
                productListByCategory[currentActiveCategory] = productCategoryList
                _viewState.postValue(
                    Event(
                        ProductListViewState.Load(
                            currentActiveCategory,
                            productCategoryList
                        )
                    )
                )
            }
        }
    }
}