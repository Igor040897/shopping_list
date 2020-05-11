package com.example.shopping_list.data

import com.example.shopping_list.data.models.Product
import io.reactivex.Flowable

interface Repository {

    val allPurchaseProducts: Flowable<List<Product>>

    val allNotPurchaseProducts: Flowable<List<Product>>

    fun saveItemProduct(product: Product)

    fun saveItemsProduct(products: List<Product>)
}