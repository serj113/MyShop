package com.serj113.data.local

import androidx.room.*
import com.serj113.data.local.entity.CartProductLocal
import com.serj113.data.local.entity.ProductLocal
import com.serj113.model.CartProduct

@Dao
interface CartProductDao {
    @Query("select * from cart_product")
    suspend fun getCart(): List<CartProductLocal>

    @Query("select * from cart_product where productId=:productId")
    suspend fun getCartByProductId(productId: Int): CartProductLocal?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg cartProduct: CartProductLocal)

    @Query("update cart_product SET quantity=:quantity WHERE id=:id")
    suspend fun updateCartProduct(id: Int, quantity: Int)

    @Delete
    suspend fun deleteCartProduct(cartProduct: CartProductLocal)

    @Query("delete from cart_product")
    suspend fun deleteAll()
}