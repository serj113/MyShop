package com.serj113.data.util

import com.serj113.model.Product
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson

class ProductArrayListJsonAdapter {
    @ToJson
    fun arrayListToJson(list: ArrayList<Product>): List<Product> = list

    @FromJson
    fun arrayListFromJson(list: List<Product>): ArrayList<Product> = ArrayList(list)
}