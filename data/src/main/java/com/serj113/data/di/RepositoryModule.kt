package com.serj113.data.di

import com.serj113.data.repository.StoreRepositoryImpl
import com.serj113.domain.repository.StoreRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    @Singleton
    internal abstract fun provideStoreRepository(
        storeRepositoryImpl: StoreRepositoryImpl
    ): StoreRepository
}