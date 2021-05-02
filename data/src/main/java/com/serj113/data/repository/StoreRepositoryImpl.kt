package com.serj113.data.repository

import com.serj113.data.api.StoreApi
import com.serj113.data.api.postmodel.CartBody
import com.serj113.data.local.CartProductDao
import com.serj113.data.local.ProductDao
import com.serj113.data.local.entity.CartProductLocal
import com.serj113.data.local.entity.toBody
import com.serj113.data.local.entity.toEntity
import com.serj113.data.local.entity.toLocal
import com.serj113.domain.repository.StoreRepository
import com.serj113.model.CartProduct
import com.serj113.model.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject

internal class StoreRepositoryImpl @Inject constructor(
    private val storeApi: StoreApi,
    private val cartProductDao: CartProductDao,
    private val productDao: ProductDao
) : StoreRepository {
    override suspend fun fetchProductList(): List<Product> = withContext(Dispatchers.IO) {
        val response = storeApi.getProductList()
        var result = listOf<Product>()
        if (response.isSuccessful && response.body() != null) {
            response.body()?.let {
                result = it
                it.map { product ->
                    productDao.insertAll(product.toLocal())
                }
            }
        }
        result
    }

    override suspend fun fetchCategoryList(): List<String> = withContext(Dispatchers.IO) {
        val response = storeApi.getCategoryList()
        var result = listOf<String>()
        if (response.isSuccessful && response.body() != null) {
            response.body()?.let {
                result = it
            }
        }
        result
    }

    override suspend fun addProductToCart(productId: Int, quantity: Int): Boolean =
        withContext(Dispatchers.IO) {
            var result = false
            val productLocal = productDao.getProductById(productId)
            val cartProductId = cartProductDao.getCartByProductId(productId)?.id ?: 0
            if (productLocal.title.isNotBlank()) {
                if (cartProductId != 0) {
                    cartProductDao.updateCartProduct(cartProductId, quantity)
                } else {
                    val cartProductLocal = CartProductLocal(
                        productId,
                        quantity,
                        productLocal.category,
                        productLocal.description,
                        0,
                        productLocal.image,
                        productLocal.price,
                        productLocal.title
                    )
                    cartProductDao.insertAll(cartProductLocal)
                }
                result = true
            }
            result
        }

    override suspend fun removeProductFromCart(productId: Int): Boolean =
        withContext(Dispatchers.IO) {
            var result = false
            val cartProduct = cartProductDao.getCartByProductId(productId)
            cartProduct?.let {
                cartProductDao.deleteCartProduct(it)
                result = true
            }
            result
        }

    override suspend fun fetchCart(): List<CartProduct> = withContext(Dispatchers.IO) {
        val response = cartProductDao.getCart()
        val result = response.toEntity()
        result
    }

    override suspend fun checkoutCart(): Boolean =
        withContext(Dispatchers.IO) {
            var result = false
            val cartProductBodyList = cartProductDao.getCart().toBody()
            val currentDate = Calendar.getInstance().time.toString()
            val cartBody = CartBody(
                currentDate,
                cartProductBodyList,
                0
            )
            val response = storeApi.checkoutCart(cartBody)
            if (response.isSuccessful && response.body() != null) {
                cartProductDao.deleteAll()
                result = true
            }
            result
        }

}