package com.serj113.domain.usecase

import com.serj113.domain.base.Entity
import com.serj113.domain.interactor.GetProductByCategoryUseCase
import com.serj113.domain.repository.StoreRepository
import com.serj113.model.CartProduct
import com.serj113.model.Product
import com.serj113.model.toCartProductList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

import javax.inject.Inject

internal class GetProductByCategoryUseCaseImpl @Inject constructor(
    private val storeRepository: StoreRepository
) : GetProductByCategoryUseCase() {
    override fun invoke() = flow {
        emit(Entity.Loading)
        val products = storeRepository.fetchProductList().toCartProductList()
        val tempResult = hashMapOf<String, MutableList<CartProduct>>()
        products.map {
            if (tempResult.containsKey(it.category)) {
                tempResult[it.category]?.add(it)
            } else {
                tempResult[it.category] = mutableListOf(it)
            }
        }
        val result = hashMapOf<String, List<CartProduct>>()
        tempResult.map {
            result[it.key] = it.value
        }
        emit(Entity.Success(result))
    }.flowOn(Dispatchers.IO)
}