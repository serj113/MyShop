package com.serj113.data.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.serj113.model.Product

@Entity(tableName = "product")
data class ProductLocal(
    @ColumnInfo(name = "category")
    var category: String = "",
    @ColumnInfo(name = "description")
    var description: String = "",
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int = 0,
    @ColumnInfo(name = "image")
    var image: String = "",
    @ColumnInfo(name = "price")
    var price: Double = 0.0,
    @ColumnInfo(name = "title")
    var title: String = ""
)

fun Product.toLocal(): ProductLocal {
    return ProductLocal(
        category, description, id, image, price, title
    )
}

fun List<Product>.toLocal(): List<ProductLocal> {
    return map { it.toLocal() }
}