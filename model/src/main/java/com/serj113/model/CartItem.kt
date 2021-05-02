package com.serj113.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CartItem(
    @field:Json(name = "date")
    var date: String = "",
    @field:Json(name = "id")
    var id: Int = 0,
    @field:Json(name = "products")
    var products: List<CartProduct> = listOf(),
    @field:Json(name = "userId")
    var userId: Int = 0,
    @field:Json(name = "__v")
    var v: Int = 0
)