package com.serj113.presentation.productlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.serj113.base.presentation.BaseFragment
import com.serj113.model.CartProduct
import com.serj113.model.Product
import com.serj113.presentation.productlist.databinding.FragmentProductListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductListFragment : BaseFragment<FragmentProductListBinding>() {

    private val viewModel: ProductListViewModel by activityViewModels()
    private var productAdapter: ProductRecyclerViewAdapter? = null
    private var categoryAdapter: CategoryRecyclerViewAdapter? = null

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentProductListBinding.inflate(inflater, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.viewState.observe(viewLifecycleOwner, {
            if (it.hasBeenHandled.not()) {
                it.getContentIfNotHandled()?.let { viewState ->
                    when (viewState) {
                        is ProductListViewState.LoadInitial -> {
                            categoryAdapter?.setCategoryList(viewState.categoryList)
                            productAdapter?.setProductList(viewState.cartProductList)
                        }
                        is ProductListViewState.Load -> {
                            productAdapter?.setProductList(viewState.cartProductList)
                        }
                        else ->  {

                        }
                    }
                }
            }
        })
        viewModel.loadInitialData()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
    }

    private fun initAdapter() {
        productAdapter = ProductRecyclerViewAdapter(listOf(), ::onClickProductToCart)
        categoryAdapter = CategoryRecyclerViewAdapter(listOf(), ::onClickCategory)
        binding?.let {
            it.rvProduct.adapter = productAdapter
            it.rvCategory.adapter = categoryAdapter
        }
    }

    private fun onClickProductToCart(cartProduct: CartProduct) {
        viewModel.onClickProductToCart(cartProduct)
    }

    private fun onClickCategory(position: Int, category: String) {
        binding?.rvCategory?.layoutManager?.scrollToPosition(position)
        viewModel.onClickCategory(category)
    }
}