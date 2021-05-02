package com.serj113.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import com.serj113.domain.base.Entity
import com.serj113.domain.interactor.GetCartUseCase
import com.serj113.domain.repository.StoreRepository
import com.serj113.domain.usecase.GetCartUseCaseImpl
import com.serj113.model.CartProduct
import fr.xgouchet.elmyr.junit4.ForgeRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GetCartUseCaseImplTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    val forge = ForgeRule()

    private val mockStoreRepository = mockk<StoreRepository>()

    private lateinit var getCartUseCase: GetCartUseCase

    @Before
    fun setup() {
        getCartUseCase = GetCartUseCaseImpl(mockStoreRepository)
    }

    @Test
    fun `search user should return list of user`() = runBlockingTest {
        val cartProductList = listOf(
            CartProduct(),
            CartProduct(),
            CartProduct(),
            CartProduct(),
            CartProduct()
        )

        coEvery {
            mockStoreRepository.fetchCart()
        } returns cartProductList

        val flow = getCartUseCase.invoke()
        val mutableCartProductList = mutableListOf<CartProduct>()

        flow.onEach { entity ->
            if (entity is Entity.Success) {
                entity.data.let {
                    mutableCartProductList.addAll(it)
                }
            }
        }.collect()
        assertThat(mutableCartProductList.count(), equalTo(cartProductList.count()))
    }
}