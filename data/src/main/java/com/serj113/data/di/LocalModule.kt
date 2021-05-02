package com.serj113.data.di

import android.content.Context
import androidx.room.Room
import com.serj113.data.local.CartProductDao
import com.serj113.data.local.ProductDao
import com.serj113.data.local.StoreDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class LocalModule {

    private lateinit var storeDatabase: StoreDatabase

    @Provides
    @Singleton
    internal fun provideStoreDatabase(@ApplicationContext appContext: Context): StoreDatabase {
        storeDatabase = Room.databaseBuilder(
            appContext,
            StoreDatabase::class.java,
            StoreDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
        return storeDatabase
    }

    @Provides
    @Singleton
    internal fun provideCartProductDao(db: StoreDatabase): CartProductDao {
        return db.cartProductDao()
    }

    @Provides
    @Singleton
    internal fun provideProductDao(db: StoreDatabase): ProductDao {
        return db.productDao()
    }
}