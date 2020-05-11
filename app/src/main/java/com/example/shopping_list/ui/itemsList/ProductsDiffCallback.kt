package com.example.shopping_list.ui.itemsList

import androidx.recyclerview.widget.DiffUtil
import com.example.shopping_list.data.models.Product

class ProductsDiffCallback(private val oldItems: ArrayList<Product>, private val newItems: List<Product>) :
    DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldItems[oldItemPosition]
        val newItem = newItems[newItemPosition]
        return oldItem.id == newItem.id
    }

    override fun getOldListSize() = oldItems.size

    override fun getNewListSize() = newItems.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldItems[oldItemPosition]
        val newItem = newItems[newItemPosition]
        return oldItem.id == newItem.id &&
                oldItem.name == newItem.name &&
                oldItem.isPurchase == newItem.isPurchase
    }
}