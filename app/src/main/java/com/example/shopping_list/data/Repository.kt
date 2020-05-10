package com.example.shopping_list.data

import androidx.lifecycle.LiveData
import com.example.shopping_list.data.models.Product
import io.reactivex.Maybe

interface Repository {

    val allPurchaseProducts: Maybe<List<Product>>

    val allNotPurchaseProducts: Maybe<List<Product>>

    fun saveItemProduct(product: Product)
}