package com.serj113.domain.interactor

import com.serj113.domain.base.Entity
import com.serj113.domain.base.FlowUseCaseWithArgs

abstract class RemoveProductFromCartUseCase :
    FlowUseCaseWithArgs<RemoveProductFromCartUseCase.Args, Entity<Boolean>>() {
    data class Args(
        val productId: Int
    )
}