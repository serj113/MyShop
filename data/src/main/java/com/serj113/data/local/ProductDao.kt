package com.serj113.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.serj113.data.local.entity.ProductLocal

@Dao
interface ProductDao {
    @Query("select * from product where id=:id")
    suspend fun getProductById(id: Int): ProductLocal

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg products: ProductLocal)
}