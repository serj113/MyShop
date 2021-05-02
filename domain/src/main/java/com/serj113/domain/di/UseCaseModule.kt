package com.serj113.domain.di

import com.serj113.domain.interactor.*
import com.serj113.domain.usecase.*
import com.serj113.domain.usecase.AddProductToCartUseCaseImpl
import com.serj113.domain.usecase.GetCartUseCaseImpl
import com.serj113.domain.usecase.GetProductByCategoryUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
abstract class UseCaseModule {

    @Binds
    @Singleton
    internal abstract fun provideGetProductByCategoryUseCase(
        getProductByCategoryUseCaseImpl: GetProductByCategoryUseCaseImpl
    ): GetProductByCategoryUseCase

    @Binds
    @Singleton
    internal abstract fun provideGetCartUseCase(
        getCartUseCaseImpl: GetCartUseCaseImpl
    ): GetCartUseCase

    @Binds
    @Singleton
    internal abstract fun provideAddProductToCartUseCase(
        addProductToCartUseCaseImpl: AddProductToCartUseCaseImpl
    ): AddProductToCartUseCase

    @Binds
    @Singleton
    internal abstract fun provideRemoveProductFromCartUseCase(
        removeProductToCartUseCaseImpl: RemoveProductFromCartUseCaseImpl
    ): RemoveProductFromCartUseCase

    @Binds
    @Singleton
    internal abstract fun provideCheckoutCartUseCase(
        checkoutCartUseCaseImpl: CheckoutCartUseCaseImpl
    ): CheckoutCartUseCase
}