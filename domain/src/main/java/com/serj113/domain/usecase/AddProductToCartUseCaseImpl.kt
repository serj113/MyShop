package com.serj113.domain.usecase

import com.serj113.domain.base.Entity
import com.serj113.domain.interactor.AddProductToCartUseCase
import com.serj113.domain.repository.StoreRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

internal class AddProductToCartUseCaseImpl @Inject constructor(
    private val storeRepository: StoreRepository
) : AddProductToCartUseCase() {
    override fun invoke(args: Args) = flow {
        emit(Entity.Loading)
        val result = storeRepository.addProductToCart(args.productId, args.quantity)
        emit(Entity.Success(result))
    }.flowOn(Dispatchers.IO)
}