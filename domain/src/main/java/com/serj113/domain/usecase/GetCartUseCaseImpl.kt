package com.serj113.domain.usecase

import com.serj113.domain.base.Entity
import com.serj113.domain.interactor.GetCartUseCase
import com.serj113.domain.repository.StoreRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

internal class GetCartUseCaseImpl @Inject constructor(
    private val storeRepository: StoreRepository
) : GetCartUseCase() {
    override fun invoke() = flow {
        emit(Entity.Loading)
        val cartProductList = storeRepository.fetchCart()
        emit(Entity.Success(cartProductList))
    }.flowOn(Dispatchers.IO)

}