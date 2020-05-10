package com.example.shopping_list.ui.main.purchase

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shopping_list.data.models.Product
import com.example.shopping_list.databinding.ItemPurchaseBinding

class PurchaseAdapter: RecyclerView.Adapter<PurchaseAdapter.PurchaseItemVH>() {

    private var items: ArrayList<Product> = ArrayList()

//    lateinit var onMessageClickListener: OnMessageItemClickListener

    fun setItems(purchases : List<Product>){
        this.items.addAll(purchases)
        notifyDataSetChanged()

//        newItems?.let {
//            val diffCallback = MessagesDiffCallback(items, it)
//            val diffResult = DiffUtil.calculateDiff(diffCallback)
//            items.clear()
//            items.addAll(it)
//            diffResult.dispatchUpdatesTo(this)
//        }
        //todo
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PurchaseItemVH {
        val binding = ItemPurchaseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PurchaseItemVH(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: PurchaseItemVH, position: Int) {
        val purchaseItem = items[position]
        holder.bind(purchaseItem)
    }

    inner class PurchaseItemVH(private val binding: ItemPurchaseBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
//            binding.root.setOnClickListener {
//                val position = if (adapterPosition < 0) {
//                    0
//                } else {
//                    adapterPosition
//                }
//                if (position < items.size) {
//                    onMessageClickListener.onMessageClicked(items[position])
//                }
//            }
        }

        fun bind(item: Product) {
            binding.apply {
                nameTextView.text = item.name
            }
        }
    }

//    interface OnMessageItemClickListener {
//        fun onMessageClicked(message: Message)
//    }
}