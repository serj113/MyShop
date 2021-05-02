package com.serj113.presentation.cart

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import com.serj113.base.presentation.BaseViewModel
import com.serj113.base.presentation.util.Event
import com.serj113.domain.base.Entity
import com.serj113.domain.interactor.CheckoutCartUseCase
import com.serj113.domain.interactor.GetCartUseCase
import com.serj113.domain.interactor.RemoveProductFromCartUseCase
import com.serj113.model.CartProduct
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CartViewModel @ViewModelInject constructor(
    private val getCartUseCase: GetCartUseCase,
    private val removeProductFromCartUseCase: RemoveProductFromCartUseCase,
    private val checkoutCartUseCase: CheckoutCartUseCase
) : BaseViewModel<CartViewState>() {
    private var cartProductList = listOf<CartProduct>()

    fun loadInitialData() = viewModelScope.launch {
        getCartUseCase.invoke().collect { entity ->
            val viewState = when (entity) {
                is Entity.Success -> {
                    cartProductList = entity.data
                    CartViewState.Load(cartProductList)
                }
                else -> {
                    CartViewState.Loading
                }
            }
            _viewState.postValue(Event(viewState))
        }
    }

    fun deleteCartProduct(cartProduct: CartProduct) = viewModelScope.launch {
        removeProductFromCartUseCase.invoke(RemoveProductFromCartUseCase.Args(cartProduct.productId))
            .collect { entity ->
                if (entity is Entity.Success && entity.data) {
                    val mutableCartProductList = cartProductList.toMutableList()
                    mutableCartProductList.remove(cartProduct)
                    cartProductList = mutableCartProductList
                    _viewState.postValue(Event(CartViewState.Load(mutableCartProductList)))
                }
            }
    }

    fun checkoutCart() = viewModelScope.launch {
        checkoutCartUseCase.invoke().collect { entity ->
            if (entity is Entity.Success && entity.data) {
                cartProductList = listOf()
                _viewState.postValue(Event(CartViewState.Load(listOf())))
            }
        }
    }
}