package com.serj113.data.api.postmodel

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CartProductBody(
    @field:Json(name = "productId")
    var productId: Int = 0,
    @field:Json(name = "quantity")
    var quantity: Int = 0
)