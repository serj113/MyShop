package com.serj113.data.api

import com.serj113.data.api.postmodel.CartBody
import com.serj113.model.CartItem
import com.serj113.model.Product
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface StoreApi {
    @GET("products")
    suspend fun getProductList(): Response<List<Product>>

    @GET("categories")
    suspend fun getCategoryList(): Response<List<String>>

    @POST("carts")
    suspend fun checkoutCart(@Body cartBody: CartBody): Response<CartItem>
}