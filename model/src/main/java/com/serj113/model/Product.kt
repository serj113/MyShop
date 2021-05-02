package com.serj113.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Product(
    @field:Json(name = "category")
    var category: String = "",
    @field:Json(name = "description")
    var description: String = "",
    @field:Json(name = "id")
    var id: Int = 0,
    @field:Json(name = "image")
    var image: String = "",
    @field:Json(name = "price")
    var price: Double = 0.0,
    @field:Json(name = "title")
    var title: String = ""
)

fun Product.toCartProduct(): CartProduct {
    return CartProduct(id, 0, category, description, image, price, title)
}

fun List<Product>.toCartProductList(): List<CartProduct> {
    return map { it.toCartProduct() }
}