package com.serj113.data.api.postmodel


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CartBody(
    @field:Json(name = "date")
    var date: String = "",
    @field:Json(name = "products")
    var products: List<CartProductBody> = listOf(),
    @field:Json(name = "userId")
    var userId: Int = 0
)