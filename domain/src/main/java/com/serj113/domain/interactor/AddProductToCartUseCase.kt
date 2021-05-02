package com.serj113.domain.interactor

import com.serj113.domain.base.Entity
import com.serj113.domain.base.FlowUseCaseWithArgs

abstract class AddProductToCartUseCase :
    FlowUseCaseWithArgs<AddProductToCartUseCase.Args, Entity<Boolean>>() {
    data class Args(
        val productId: Int,
        val quantity: Int
    )
}