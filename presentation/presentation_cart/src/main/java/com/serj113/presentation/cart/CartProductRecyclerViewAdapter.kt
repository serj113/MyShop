package com.serj113.presentation.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.serj113.model.CartProduct
import com.serj113.presentation.cart.databinding.CartListItemBinding

class CartProductRecyclerViewAdapter(
    private var cartProductList: List<CartProduct>,
    private val onClickDelete: (CartProduct) -> Unit
) : RecyclerView.Adapter<CartProductRecyclerViewAdapter.CartItemViewHolder>() {

    fun setCartProductList(cartProductList: List<CartProduct>) {
        this.cartProductList = cartProductList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartItemViewHolder {
        return CartItemViewHolder(
            CartListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onClickDelete
        )
    }

    override fun onBindViewHolder(holder: CartItemViewHolder, position: Int) {
        val product = cartProductList[position]
        holder.bind(product)
    }

    override fun getItemCount() = cartProductList.size

    inner class CartItemViewHolder(
        private val binding: CartListItemBinding,
        private val onClickDelete: (CartProduct) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(cartProduct: CartProduct) {
            binding.tvCartProductName.text = cartProduct.title
            binding.btnDelete.setOnClickListener { onClickDelete.invoke(cartProduct) }
            Glide
                .with(binding.root)
                .load(cartProduct.image)
                .into(binding.ivCartProductImage)
        }
    }
}