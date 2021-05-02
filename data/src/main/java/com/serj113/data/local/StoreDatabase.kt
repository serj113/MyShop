package com.serj113.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.serj113.data.local.entity.CartProductLocal
import com.serj113.data.local.entity.ProductLocal

@Database(
    entities = [CartProductLocal::class, ProductLocal::class],
    version = 1,
    exportSchema = false
)
abstract class StoreDatabase : RoomDatabase() {
    abstract fun cartProductDao(): CartProductDao
    abstract fun productDao(): ProductDao

    companion object {
        internal const val DATABASE_NAME = "store_database"
    }
}