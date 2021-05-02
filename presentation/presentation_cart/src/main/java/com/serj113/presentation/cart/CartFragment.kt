package com.serj113.presentation.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.serj113.base.presentation.BaseFragment
import com.serj113.model.CartProduct
import com.serj113.presentation.cart.databinding.FragmentCartBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : BaseFragment<FragmentCartBinding>() {
    private val viewModel: CartViewModel by activityViewModels()
    private var adapter: CartProductRecyclerViewAdapter? = null

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentCartBinding.inflate(layoutInflater, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding?.btnCheckout?.setOnClickListener { viewModel.checkoutCart() }
        viewModel.viewState.observe(viewLifecycleOwner, {
            if (it.hasBeenHandled.not()) {
                it.getContentIfNotHandled()?.let { viewState ->
                    if (viewState is CartViewState.Load) {
                        adapter?.setCartProductList(viewState.cartProductList)
                        binding?.btnCheckout?.visibility =
                            if (viewState.cartProductList.isEmpty()) View.INVISIBLE else View.VISIBLE
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
        adapter = CartProductRecyclerViewAdapter(listOf(), ::onClickDelete)
        binding?.let {
            it.rvCartProduct.adapter = adapter
        }
    }

    private fun onClickDelete(cartProduct: CartProduct) {
        viewModel.deleteCartProduct(cartProduct)
    }
}