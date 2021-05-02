package com.serj113.presentation.productlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.serj113.model.Product
import com.serj113.presentation.productlist.databinding.ProductListItemBinding

class ProductPagingAdapter(
    private val onItemClick: (Product) -> Unit
) : PagingDataAdapter<Product, ProductPagingAdapter.ProductItemViewHolder>(ProductItemCallback) {

    override fun onBindViewHolder(holder: ProductItemViewHolder, position: Int) {
        getItem(position)?.let { product ->
            holder.bind(product)
            holder.itemView.setOnClickListener { onItemClick.invoke(product) }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductItemViewHolder {
        return ProductItemViewHolder(
            ProductListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    inner class ProductItemViewHolder(
        private val binding: ProductListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            binding.tvProductName.text = product.title
            Glide
                .with(binding.root)
                .load(product.image)
                .into(binding.ivProductImage)
        }
    }
}