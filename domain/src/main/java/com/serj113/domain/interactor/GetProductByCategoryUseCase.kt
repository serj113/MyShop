package com.serj113.domain.interactor

import com.serj113.domain.base.Entity
import com.serj113.domain.base.FlowUseCase
import com.serj113.model.CartProduct
import com.serj113.model.Product

abstract class GetProductByCategoryUseCase : FlowUseCase<Entity<HashMap<String, List<CartProduct>>>>()