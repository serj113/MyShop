package com.serj113.presentation.productlist

import androidx.recyclerview.widget.DiffUtil
import com.serj113.model.Product

object ProductItemCallback : DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Product, newItem: Product) = oldItem == newItem
}