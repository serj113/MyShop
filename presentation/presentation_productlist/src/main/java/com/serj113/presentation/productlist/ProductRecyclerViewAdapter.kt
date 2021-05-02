package com.serj113.presentation.productlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.serj113.model.CartProduct
import com.serj113.presentation.productlist.databinding.ProductListItemBinding

class ProductRecyclerViewAdapter(
    private var cartProductList: List<CartProduct>,
    private val onClickProductToCart: (CartProduct) -> Unit
) : RecyclerView.Adapter<ProductRecyclerViewAdapter.ProductItemViewHolder>() {

    fun setProductList(cartProductList: List<CartProduct>) {
        this.cartProductList = cartProductList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductItemViewHolder {
        return ProductItemViewHolder(
            ProductListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onClickProductToCart
        )
    }

    override fun onBindViewHolder(holder: ProductItemViewHolder, position: Int) {
        val cartProduct = cartProductList[position]
        holder.bind(cartProduct)
        holder.itemView.setOnClickListener { onClickProductToCart.invoke(cartProduct) }
    }

    override fun getItemCount() = cartProductList.size

    inner class ProductItemViewHolder(
        private val binding: ProductListItemBinding,
        private val onClickProductToCart: (CartProduct) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(cartProduct: CartProduct) {
            binding.tvProductName.text = cartProduct.title
            binding.btnBuy.setOnClickListener { onClickProductToCart.invoke(cartProduct) }
            Glide
                .with(binding.root)
                .load(cartProduct.image)
                .into(binding.ivProductImage)
        }
    }
}