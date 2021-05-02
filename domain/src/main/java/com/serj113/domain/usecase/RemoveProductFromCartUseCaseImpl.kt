package com.serj113.domain.usecase

import com.serj113.domain.base.Entity
import com.serj113.domain.interactor.RemoveProductFromCartUseCase
import com.serj113.domain.repository.StoreRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RemoveProductFromCartUseCaseImpl @Inject constructor(
    private val storeRepository: StoreRepository
) : RemoveProductFromCartUseCase() {
    override fun invoke(args: Args) = flow {
        emit(Entity.Loading)
        val result = storeRepository.removeProductFromCart(args.productId)
        emit(Entity.Success(result))
    }.flowOn(Dispatchers.IO)
}