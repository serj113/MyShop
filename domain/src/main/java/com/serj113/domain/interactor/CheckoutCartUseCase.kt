package com.serj113.domain.interactor

import com.serj113.domain.base.Entity
import com.serj113.domain.base.FlowUseCase

abstract class CheckoutCartUseCase : FlowUseCase<Entity<Boolean>>()