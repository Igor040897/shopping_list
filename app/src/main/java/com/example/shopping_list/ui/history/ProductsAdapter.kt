package com.example.shopping_list.ui.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.shopping_list.data.models.Product
import com.example.shopping_list.databinding.ItemProductBinding
import com.example.shopping_list.ui.itemsList.ProductsDiffCallback

open class ProductsAdapter: RecyclerView.Adapter<ProductsAdapter.ProductItemVH>() {

    protected var items: ArrayList<Product> = ArrayList()

    fun setItems(products : List<Product>){
        val diffCallback =
            ProductsDiffCallback(
                items,
                products
            )
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        items.clear()
        items.addAll(products)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductItemVH {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductItemVH(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ProductItemVH, position: Int) {
        val productItem = items[position]
        holder.bind(productItem)
    }

    open inner class ProductItemVH(private val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {

        open fun bind(item: Product) {
            binding.apply {
                nameTextView.text = item.name
            }
        }
    }}