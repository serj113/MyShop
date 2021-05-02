package com.serj113.presentation.productlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.serj113.presentation.productlist.databinding.CategoryListItemBinding

class CategoryRecyclerViewAdapter(
    private var categoryList: List<String>,
    private val onItemClick: (Int, String) -> Unit
) : RecyclerView.Adapter<CategoryRecyclerViewAdapter.CategoryItemViewHolder>() {

    fun setCategoryList(categoryList: List<String>) {
        this.categoryList = categoryList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryItemViewHolder {
        return CategoryItemViewHolder(
            CategoryListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CategoryItemViewHolder, position: Int) {
        val category = categoryList[position]
        holder.bind(category)
        holder.itemView.setOnClickListener { onItemClick.invoke(position, category) }
    }

    override fun getItemCount() = categoryList.size

    inner class CategoryItemViewHolder(
        private val binding: CategoryListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(category: String) {
            binding.tvCategoryName.text = category
        }
    }
}