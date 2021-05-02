package com.serj113.domain.usecase

import com.serj113.domain.base.Entity
import com.serj113.domain.interactor.CheckoutCartUseCase
import com.serj113.domain.repository.StoreRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CheckoutCartUseCaseImpl @Inject constructor(
    private val storeRepository: StoreRepository
) : CheckoutCartUseCase() {
    override fun invoke() = flow {
        emit(Entity.Loading)
        val result = storeRepository.checkoutCart()
        emit(Entity.Success(result))
    }.flowOn(Dispatchers.IO)
}