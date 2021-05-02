package com.serj113.data.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.serj113.data.api.postmodel.CartProductBody
import com.serj113.model.CartProduct

@Entity(tableName = "cart_product")
data class CartProductLocal(
    @ColumnInfo(name = "productId")
    var productId: Int = 0,
    @ColumnInfo(name = "quantity")
    var quantity: Int = 0,
    @ColumnInfo(name = "category")
    var category: String = "",
    @ColumnInfo(name = "description")
    var description: String = "",
    @PrimaryKey(autoGenerate = true)
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

fun CartProductLocal.toEntity(): CartProduct {
    return CartProduct(
        productId, quantity, category, description, image, price, title
    )
}

fun List<CartProductLocal>.toEntity(): List<CartProduct> {
    return map { it.toEntity() }
}

fun CartProductLocal.toBody(): CartProductBody {
    return CartProductBody(productId, quantity)
}

fun List<CartProductLocal>.toBody(): List<CartProductBody> {
    return map { it.toBody() }
}