package com.serj113.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CartProduct(
    @field:Json(name = "productId")
    var productId: Int = 0,
    @field:Json(name = "quantity")
    var quantity: Int = 0,
    var category: String = "",
    var description: String = "",
    var image: String = "",
    var price: Double = 0.0,
    var title: String = ""
)